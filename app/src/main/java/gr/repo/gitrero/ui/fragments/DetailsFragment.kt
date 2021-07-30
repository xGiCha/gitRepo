package gr.repo.gitrero.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import gr.repo.gitrero.MyApplication.Companion.job
import gr.repo.gitrero.MyApplication.Companion.scope
import gr.repo.gitrero.adapters.CommitsAdapter
import gr.repo.gitrero.database.ReposEntity
import gr.repo.gitrero.databinding.FragmentDetailsBinding
import gr.repo.gitrero.util.Constants.Companion.DATABASE_NAME
import gr.repo.gitrero.util.stringToDate
import gr.repo.gitrero.viewmodels.RepoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import java.util.*

class DetailsFragment : Fragment() {

    private lateinit var repoViewModel: RepoViewModel
    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private var repoID: String? = null
    private var ownerName: String? = null
    private var repositoryName: String? = null
    private lateinit var adapter: CommitsAdapter
    private var commitsList = mutableListOf<ReposEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repoViewModel = ViewModelProvider(requireActivity()).get(RepoViewModel::class.java)
        repoID = args.id
        ownerName = args.owner
        repositoryName = args.repositoryName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setUpObservers()
    }

    private fun setUpObservers() {
        repoViewModel.commits.observe(viewLifecycleOwner, Observer {
            it.data?.let {
                println("asdasd ${it}")
                adapter.updateItems(it)
                val reposEntity = ReposEntity(it, "$ownerName/$repositoryName")
                repoViewModel.insertCommits(reposEntity)
            }
        })
    }

    private fun initViews() {
        binding.repoId = repoID
        adapter = CommitsAdapter(requireContext(), mutableListOf())
        binding.commitsRV.layoutManager = LinearLayoutManager(requireContext())
        binding.commitsRV.adapter = adapter
        readFromDB()
    }

    private fun readFromDB() {
        lifecycleScope.launch {
            repoViewModel.readCommits("$ownerName/$repositoryName").observe(viewLifecycleOwner, Observer {
                commitsList = it.toMutableList()
                if(commitsList.size == 0 || compareDates() > 5){
                    ownerName?.let { repositoryName?.let { it1 -> repoViewModel.getCommits(it, it1) } }
                }else{
                    adapter.updateItems(it[0].commitList)
                }
            })
        }
    }

    private fun compareDates(): Int{
        if(commitsList.size > 0){
            commitsList[0].let {
                val diff = hoursDifference(Date(), stringToDate(it.date))
                println("aaaaa ${diff}")
                return diff
            }
        }
        return -1
    }

    private fun hoursDifference(date1: Date, date2: Date): Int {
        val milliToMinutes : Long = 1000 * 60
        return ((date1.time - date2.time) / milliToMinutes).toInt()
    }

//    override fun onPause() {
//        super.onPause()
//        stopUpdates()
//    }
//
//    fun startUpdates() {
//        stopUpdates()
//        job = scope.launch(Dispatchers.IO) {
//            while(true) {
//                println("asdasdasdasd")
//                delay(5000)
//            }
//        }
//    }
//
//    fun stopUpdates() {
//        job?.cancel()
//        job = null
//    }
}