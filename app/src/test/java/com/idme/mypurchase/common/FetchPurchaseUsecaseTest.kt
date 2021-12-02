package com.idme.mypurchase.common

import com.idme.mypurchase.network.ProfileSchema
import com.idme.mypurchase.network.PurchaseAPI
import com.idme.mypurchase.network.PurchaseSchema
import okhttp3.Request
import okio.Timeout
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.hamcrest.core.IsNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class FetchOrderUseCaseTest {

    lateinit var SUB: FetchPurchaseUsecase

    @Mock
    lateinit var purchaseAPI: PurchaseAPI

    @Mock
    lateinit var utils: Utils

    @Before
    fun setUp() {
        SUB = FetchPurchaseUsecase(purchaseAPI, utils)
    }

    @Test
    fun fetchProfile_successCall_resNotNull() {

        // Arrange
        val testCall = ProfileTestCall()
        testCall.success = true

        Mockito.`when`(
            purchaseAPI.fetchProfile()
        ).thenReturn(testCall)

        // Act
        SUB.fetchProfile { res, error ->
            // Assert
            MatcherAssert.assertThat(res, Is.`is`(IsNull.notNullValue()))
        }
    }

    @Test
    fun fetchProfile_failCall_returnError() {

        // Arrange
        val testCall = ProfileTestCall()
        testCall.success = false

        Mockito.`when`(
            purchaseAPI.fetchProfile()
        ).thenReturn(testCall)

        // Act
        SUB.fetchProfile { res, error ->
            // Assert
            MatcherAssert.assertThat(error, Is.`is`(IsNull.notNullValue()))
        }
    }

    @Test
    fun fetchPurchase_successCall_returnResult() {

        // Arrange
        val testCall = PurchaseTestCall()
        testCall.success = true

        Mockito.`when`(
            purchaseAPI.fetchPurchase()
        ).thenReturn(testCall)

        // Act
        SUB.fetchPurchase { res, error ->
            // Assert
            MatcherAssert.assertThat(res, Is.`is`(IsNull.notNullValue()))
        }

    }

    @Test
    fun fetchPurchase_failCall_returnResult() {

        // Arrange
        val testCall = PurchaseTestCall()
        testCall.success = false

        Mockito.`when`(
            purchaseAPI.fetchPurchase()
        ).thenReturn(testCall)

        // Act
        SUB.fetchPurchase { res, error ->
            // Assert
            MatcherAssert.assertThat(error, Is.`is`(IsNull.notNullValue()))
        }

    }


}

class PurchaseTestCall : Call<List<PurchaseSchema>?> {

    var success = true
    var isKitchenClosed = false

    var testData = mutableListOf(
        PurchaseSchema("", "", "", 40.0f, "", "", "", false)
    )

    override fun enqueue(callback: Callback<List<PurchaseSchema>?>) {

        val response = Mockito.mock(Response::class.java) as Response<List<PurchaseSchema>?>

        if (!isKitchenClosed) {
            Mockito.`when`(response.body()).thenReturn(testData)
        } else {
            Mockito.`when`(response.body()).thenReturn(emptyList())
        }

        if (success) {
            callback.onResponse(this, response)
        } else {
            callback.onFailure(this, Throwable("Error"))
        }
    }

    override fun isExecuted(): Boolean {
        TODO("Not yet implemented")
    }

    override fun clone(): Call<List<PurchaseSchema>?> {
        TODO("Not yet implemented")
    }

    override fun isCanceled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }

    override fun execute(): Response<List<PurchaseSchema>?> {
        TODO("Not yet implemented")
    }

    override fun request(): Request {
        TODO("Not yet implemented")
    }

    override fun timeout(): Timeout {
        TODO("Not yet implemented")
    }
}

class ProfileTestCall : Call<ProfileSchema?> {

    var success = true

    var testData = ProfileSchema("", "", "", "", "", "", "", "")

    override fun enqueue(callback: Callback<ProfileSchema?>) {

        val response = Mockito.mock(Response::class.java) as Response<ProfileSchema?>

        Mockito.`when`(response.body()).thenReturn(testData)

        if (success) {
            callback.onResponse(this, response)
        } else {
            callback.onFailure(this, Throwable("Error"))
        }
    }

    override fun isExecuted(): Boolean {
        TODO("Not yet implemented")
    }

    override fun clone(): Call<ProfileSchema?> {
        TODO("Not yet implemented")
    }

    override fun isCanceled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }

    override fun execute(): Response<ProfileSchema?> {
        TODO("Not yet implemented")
    }

    override fun request(): Request {
        TODO("Not yet implemented")
    }

    override fun timeout(): Timeout {
        TODO("Not yet implemented")
    }
}

