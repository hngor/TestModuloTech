package com.example.testmodulotech.ui.myaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testmodulotech.R
import com.example.testmodulotech.databinding.FragmentMyAccountBinding
import com.example.testmodulotech.ui.MainActivity
import com.example.testmodulotech.ui.myaccount.model.MyAccountUiModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyAccountFragment : Fragment() {

    private val viewModel: MyAccountViewModel by viewModel()

    private lateinit var binding: FragmentMyAccountBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarMyAccount.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        binding.toolbarMyAccount.setNavigationOnClickListener { closeFragment() }


        binding.btnSave.setOnClickListener {
            viewModel.saveUser(
                firstName = binding.editFirstName.editText?.text.toString(),
                lastName = binding.editLastName.editText?.text.toString(),
                birthDate = binding.editBirthDate.editText?.text.toString(),
                postalCode = binding.editPostalCode.editText?.text.toString(),
                streetCode = binding.editStreetCode.editText?.text.toString(),
                street = binding.editStreet.editText?.text.toString(),
                city = binding.editCity.editText?.text.toString(),
                country = binding.editCountry.editText?.text.toString()
            )
        }

        viewModel.data.observe(viewLifecycleOwner) { state -> updateUI(state) }

        viewModel.getUser()
    }

    private fun closeFragment() {
        (activity as MainActivity).hideKeyboard()
        findNavController().popBackStack()
    }

    private fun updateUI(state: MyAccountUiModel) {
        when (state) {
            is MyAccountUiModel.MyAccountData -> {
                binding.editFirstName.editText?.setText(state.firstName)
                binding.editLastName.editText?.setText(state.lastName)
                binding.editBirthDate.editText?.setText(state.birthDate)
                binding.editStreetCode.editText?.setText(state.streetCode)
                binding.editStreet.editText?.setText(state.street)
                binding.editPostalCode.editText?.setText(state.postalCode)
                binding.editCity.editText?.setText(state.city)
                binding.editCountry.editText?.setText(state.country)
            }
            MyAccountUiModel.UserInfoError -> {
                Toast.makeText(context, R.string.error_message_my_account, Toast.LENGTH_LONG).show()
            }
            MyAccountUiModel.UserInfosSaved -> Toast.makeText(
                context,
                R.string.my_account_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}