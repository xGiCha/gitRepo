package gr.repo.gitrero.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import gr.repo.gitrero.adapters.CommitsAdapter
import gr.repo.gitrero.databinding.FragmentDetailsBinding
import gr.repo.gitrero.viewmodels.RepoViewModel

class DetailsFragment : Fragment() {

    private lateinit var repoViewModel: RepoViewModel
    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private var repoID: String? = null
    private var ownerName: String? = null
    private var repositoryName: String? = null
    private lateinit var adapter: CommitsAdapter

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
            }
        })
    }

    private fun initViews() {
        binding.repoId = repoID
        adapter = CommitsAdapter(requireContext(), mutableListOf())
        binding.commitsRV.layoutManager = LinearLayoutManager(requireContext())
        binding.commitsRV.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        ownerName?.let { repositoryName?.let { it1 -> repoViewModel.getCommits(it, it1) } }
    }

}