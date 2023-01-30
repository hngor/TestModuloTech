package com.example.testmodulotech.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testmodulotech.R
import com.example.testmodulotech.domain.model.Device
import com.example.testmodulotech.domain.model.Heater
import com.example.testmodulotech.domain.model.Light
import com.example.testmodulotech.domain.model.RollerShutter
import com.example.testmodulotech.util.ProductTypeEnum

class HomePageAdapter(
    private val onDeviceClick: ((Int) -> Unit)?,
    private val onDeleteDevice: ((Int) -> Unit)?
) :
    RecyclerView.Adapter<DeviceViewHolder>() {

    internal var deviceList: List<Device> = listOf()

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = deviceList[position]
        val context = holder.itemView.context

        holder.txtDeviceName?.text = context.getString(R.string.name, device.deviceName)
        holder.txtProductType?.text = context.getString(R.string.type, device.productType.value)

        //Reset views visibility
        holder.txtIntensity?.visibility = View.VISIBLE
        holder.txtMode?.visibility = View.VISIBLE
        holder.txtTemperature?.visibility = View.VISIBLE
        holder.txtPosition?.visibility = View.VISIBLE

        when (device.productType) {
            ProductTypeEnum.LIGHT -> {
                val light = device as Light
                holder.txtIntensity?.text = context.getString(R.string.intensity_text, light.intensity)
                holder.txtMode?.text = context.getString(R.string.mode_text, light.mode)
                holder.txtTemperature?.visibility = View.GONE
                holder.txtPosition?.visibility = View.GONE
            }
            ProductTypeEnum.ROLLERSHUTTER -> {
                val rollerShutter = device as RollerShutter
                holder.txtPosition?.text =
                    context.getString(R.string.position_text, rollerShutter.position)
                holder.txtIntensity?.visibility = View.GONE
                holder.txtMode?.visibility = View.GONE
                holder.txtTemperature?.visibility = View.GONE
            }
            ProductTypeEnum.HEATER -> {
                val heater = device as Heater
                holder.txtMode?.text = context.getString(R.string.mode_text, heater.mode)
                holder.txtTemperature?.text =
                    context.getString(R.string.temperature_text, heater.temperature.toFloat())
                holder.txtPosition?.visibility = View.GONE
                holder.txtIntensity?.visibility = View.GONE
            }
        }

        holder.itemView.setOnClickListener { onDeviceClick?.invoke(device.id) }
        holder.imgDelete?.setOnClickListener { onDeleteDevice?.invoke(device.id) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.device_row, parent, false)
        return DeviceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return deviceList.count()
    }
}

class DeviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var txtDeviceName: TextView? = view.findViewById(R.id.txtDeviceName)
    var txtProductType: TextView? = view.findViewById(R.id.txtProductType)
    var txtMode: TextView? = view.findViewById(R.id.txtMode)
    var txtIntensity: TextView? = view.findViewById(R.id.txtIntensity)
    var txtPosition: TextView? = view.findViewById(R.id.txtPosition)
    var txtTemperature: TextView? = view.findViewById(R.id.txtTemperature)
    var imgDelete: ImageView? = view.findViewById(R.id.imgDelete)
}