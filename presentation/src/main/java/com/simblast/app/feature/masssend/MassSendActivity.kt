package com.simblast.app.feature.masssend

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.simblast.app.R
import com.simblast.app.common.base.QkThemedActivity
import com.simblast.app.util.PhoneNumberUtils
import kotlinx.android.synthetic.main.mass_send_activity.*
import javax.inject.Inject

class MassSendActivity : QkThemedActivity() {

    companion object {
        private const val REQUEST_PERMISSIONS_CODE = 101
    }

    private val phoneNumberUtils: PhoneNumberUtils by lazy { PhoneNumberUtils(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mass_send_activity)
        showBackButton(true)


        send_button.setOnClickListener {
            attemptSend()
        }
    }

    private fun attemptSend() {
        val numbersText = numbers_input.text.toString()
        val messageText = message_input.text.toString()

        if (messageText.isBlank()) {
            Toast.makeText(this, R.string.mass_send_no_message, Toast.LENGTH_SHORT).show()
            return
        }

        val numbers = phoneNumberUtils.extractNumbers(numbersText)
        if (numbers.isEmpty()) {
            Toast.makeText(this, R.string.mass_send_no_numbers, Toast.LENGTH_SHORT).show()
            return
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE),
                REQUEST_PERMISSIONS_CODE
            )
        } else {
            performSend(numbers, messageText)
        }
    }

    private fun performSend(numbers: List<String>, message: String) {
        val smsManager = SmsManager.getDefault()
        val parts = smsManager.divideMessage(message)
        for (num in numbers) {
            for (part in parts) {
                smsManager.sendTextMessage(num, null, part, null, null)
            }
        }
        Toast.makeText(this, getString(R.string.mass_send_sent, numbers.size), Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSIONS_CODE) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                attemptSend()
            } else {
                Toast.makeText(this, R.string.mass_send_permission_denied, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
