package com.example.user.kotsalesapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import kotlinx.android.synthetic.main.activity_total.*
import java.math.BigDecimal


class TotalAct : AppCompatActivity() {

    var total: Double = 0.0
    var config: PayPalConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total)

        total = UserInfo.qty * UserInfo.price
        total_tv.text = total.toString()

        config = PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(UserInfo.clientid)
        //.environment(PayPalConfiguration.ENVIRONMENT_PRODUCTION)
        var i = Intent(this, PayPalService::class.java)
        i.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        startService(i)


        total_paypal.setOnClickListener {
            var payment = PayPalPayment(BigDecimal.valueOf(total)
                    , "USD", "Food App",
                    PayPalPayment.PAYMENT_INTENT_SALE)

            var my_int = Intent(this, PaymentActivity::class.java)
            my_int.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
            my_int.putExtra(PaymentActivity.EXTRA_PAYMENT, payment)
            startActivityForResult(my_int, 123)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Thank you",
                        Toast.LENGTH_LONG).show()
            }
        }
    }
}
