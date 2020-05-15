package br.com.kotlin.webinar

import java.lang.Integer.parseInt
import java.math.BigDecimal

sealed class FeeType(open val name: String)
data class DeliveryFee(override val name: String = "delivery fee") : FeeType(name)
data class PaymentFee(override val name: String = "payment fee") : FeeType(name)

sealed class Country(val xSalesChannel: String) {
    override fun toString(): String = this.xSalesChannel
}
object Germany : Country("DE")
object Italy : Country("IT")
object Japan : Country("JP")

sealed class Fee(open val price: BigDecimal, open val type: FeeType, open val country: Country)
data class GermanyDeliveryFee(override val price: BigDecimal,
                              override val type: FeeType = DeliveryFee(),
                              override val country: Country = Germany) : Fee(price, type, country)

data class GermanyPaymentFee(override val price: BigDecimal,
                             override val type: FeeType = PaymentFee(),
                             override val country: Country = Germany) : Fee(price, type, country)

data class JapanDeliveryFee(override val price: BigDecimal,
                            override val type: FeeType = PaymentFee(),
                            override val country: Country = Japan,
                            val japanCode: Int = 30) : Fee(price, type, country)

data class ItalyDeliveryFee(override val price: BigDecimal,
                            override val type: FeeType = PaymentFee(),
                            override val country: Country = Italy) : Fee(price, type, country)


fun main() {
    val fees: List<Fee> = listOf(
            GermanyDeliveryFee(BigDecimal("1.10")),
            GermanyPaymentFee(BigDecimal("2.10")),
            JapanDeliveryFee(BigDecimal("3.10")),
            ItalyDeliveryFee(BigDecimal("4.10"))
                                                                         )

    println("1 @ begin ---------------------------")
    fees.forEach { fee ->
        when (fee) {
            is GermanyDeliveryFee -> {
                val (price, type, country ) = fee
                println("I'm GermanyDeliveryFee $fee @ $price @ $type @ $country")
            }
            is GermanyPaymentFee -> {
                println("I'm GermanyPaymentFee $fee")
            }
            is JapanDeliveryFee -> {
                println("I'm JapanDeliveryFee @ japanCode: ${fee.japanCode}")
            }
            is ItalyDeliveryFee -> {
                println("I'm ItalyDeliveryFee $fee")
            }
        }
    }
    println("1 @ end   ---------------------------\n")

    println("2 @ begin ---------------------------")
    fees.forEach { fee ->
        when (fee) {
            GermanyDeliveryFee(BigDecimal("1.10")) -> {
                println("I'm DeliveryFeeGermany")
            }

//            GermanyPaymentFee(_) -> {
//
//            }
        }
    }
    println("2 @ end   ---------------------------")


//    println("2 @ begin ---------------------------")
//    println("2 @ end   ---------------------------")

    val x = 1
    when (x) {
        parseInt("1") -> { println("parse 1") }
    }

}