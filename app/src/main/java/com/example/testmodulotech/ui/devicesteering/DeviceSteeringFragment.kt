package com.example.testmodulotech.ui.devicesteering

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testmodulotech.R
import com.example.testmodulotech.databinding.FragmentDeviceSteeringBinding
import com.example.testmodulotech.domain.model.Heater
import com.example.testmodulotech.domain.model.Light
import com.example.testmodulotech.domain.model.RollerShutter
import com.example.testmodulotech.ui.devicesteering.model.DeviceSteeringUiModel
import com.example.testmodulotech.util.Constants
import com.example.testmodulotech.util.ProductTypeEnum
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeviceSteeringFragment : Fragment() {

    private val viewModel: DeviceSteeringViewModel by viewModel()
    private lateinit var binding: FragmentDeviceSteeringBinding
    private val navArguments: DeviceSteeringFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.data.observe(this) { state -> updateUI(state) }
        viewModel.getDevice(navArguments.deviceId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDeviceSteeringBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init toolbar
        binding.deviceSteeringToolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        binding.deviceSteeringToolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        //listeners
        binding.sliderHeaterTemperature.addOnChangeListener { _, value, _ ->
            binding.txtHeaterTemperature.text = "$value"
        }
    }

    private fun updateUI(state: DeviceSteeringUiModel) {
        resetLayoutsVisibility()
        when(state) {
            is DeviceSteeringUiModel.DeviceData -> {
                binding.txtSteeringDeviceName.text = state.device.deviceName
                when(state.device.productType) {
                    ProductTypeEnum.LIGHT -> {
                        val light = state.device as Light
                        binding.lightSettingsLayout.visibility = View.VISIBLE
                        binding.sliderLightIntensity.value = light.intensity.toFloat()
                        binding.switchLightMode.isEnabled = light.mode == Constants.MODE_ON
                    }
                    ProductTypeEnum.ROLLERSHUTTER -> {
                        val rollerShutter = state.device as RollerShutter
                        binding.rollerShutterSettingsLayout.visibility = View.VISIBLE
                        binding.sliderRollerShutterPosition.value = rollerShutter.position.toFloat()
                    }
                    ProductTypeEnum.HEATER -> {
                        val heater = state.device as Heater
                        binding.heaterSettingsLayout.visibility = View.VISIBLE
                        binding.switchHeaterMode.isEnabled = heater.mode == Constants.MODE_ON
                        binding.sliderHeaterTemperature.value = heater.temperature.toFloat()
                        binding.txtHeaterTemperature.text = "${heater.temperature}"
                    }
                }
            }
            DeviceSteeringUiModel.DeviceUnknown -> {
                binding.unknownDeviceLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun resetLayoutsVisibility() {
        binding.heaterSettingsLayout.visibility = View.GONE
        binding.lightSettingsLayout.visibility = View.GONE
        binding.rollerShutterSettingsLayout.visibility = View.GONE
    }
}