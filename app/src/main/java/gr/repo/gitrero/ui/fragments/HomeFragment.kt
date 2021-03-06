package gr.repo.gitrero.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import gr.repo.gitrero.R
import gr.repo.gitrero.models.repository.Repo
import gr.repo.gitrero.util.Constants.Companion.ERROR
import gr.repo.gitrero.util.NetworkResult
import gr.repo.gitrero.util.hideKeyboard
import gr.repo.gitrero.util.showKeyboard
import gr.repo.gitrero.viewmodels.RepoViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent


class HomeFragment : Fragment() {

    private lateinit var repoViewModel: RepoViewModel
    private var mRepo: Repo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repoViewModel = ViewModelProvider(requireActivity()).get(RepoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()
        setUpListeners()
    }

    private fun setUpListeners() {

        container.setOnClickListener {
            hideKeyboard()
            ownerTxtV.clearFocus()
            repositoryTxtV.clearFocus()
        }

        nextBtn.setOnClickListener {
            repoViewModel.getRepositories(ownerTxtV.text.toString(), repositoryTxtV.text.toString())
        }
    }

    private fun setUpObservers() {
        repoViewModel.repos.observe(viewLifecycleOwner, Observer {
            when (it.message) {
                ERROR -> {
                    errorTxtV.visibility = View.VISIBLE
                }
                else -> {
                    it.data?.let { repo ->
                        mRepo = repo
                        val fullRepoPath = "${ownerTxtV.text}/${repositoryTxtV.text}"
                        if (mRepo?.fullName == fullRepoPath) {
                            mRepo?.let {
                                findNavController().navigate(
                                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                                        it.id.toString(),
                                        owner = it.owner.login,
                                        repositoryName = it.name
                                    )
                                )
                            }
                            errorTxtV.visibility = View.GONE
                        } else {
                            errorTxtV.visibility = View.VISIBLE
                        }
                    }
                }
            }
        })
    }
}