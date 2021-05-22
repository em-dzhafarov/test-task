package com.dzhafarov.testtaskapplication.presentation.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dzhafarov.testtaskapplication.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private val viewModel: RegistrationViewModel by viewModels()

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!! // must be invoked within onCreateView() - onDestroyView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSignUp.setOnClickListener { onSignUpButtonClicked() }
        viewModel.errorLiveData.observe(viewLifecycleOwner, this::showErrorMessage)
        viewModel.successLiveData.observe(viewLifecycleOwner) { navigateNext() }
        viewModel.progressLiveData.observe(viewLifecycleOwner, this::showOrHideProgress)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun onSignUpButtonClicked() {
        val username = binding.editUsername.text.toString()
        val email = binding.editEmail.text.toString()
        viewModel.register(username, email)
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateNext() {
        val action = RegistrationFragmentDirections.actionRegistrationFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    private fun showOrHideProgress(isShow: Boolean) = with(binding) {
        if (isShow) {
            groupContent.visibility = View.GONE
            progressCircular.visibility = View.VISIBLE
        } else {
            groupContent.visibility = View.VISIBLE
            progressCircular.visibility = View.GONE
        }
    }
}
