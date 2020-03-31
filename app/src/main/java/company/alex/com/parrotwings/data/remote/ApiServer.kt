package company.alex.com.parrotwings.data.remote

import company.alex.com.parrotwings.data.remote.request.AuthUserRequest
import company.alex.com.parrotwings.data.remote.request.SearchUserRequest
import company.alex.com.parrotwings.data.remote.request.TransactionRequest
import company.alex.com.parrotwings.data.remote.response.AuthUserResponse
import company.alex.com.parrotwings.data.remote.response.TransactionHistoryResponse
import company.alex.com.parrotwings.data.remote.response.UserInfoResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServer {

    @POST(Endpoints.LOGIN)
    fun login(@Body authUserRequest: AuthUserRequest): Single<AuthUserResponse>

    @POST(Endpoints.REGISTRATION)
    fun registration(@Body authUserRequest: AuthUserRequest): Single<AuthUserResponse>

    @GET(Endpoints.RETRIEVE_TRANSACTIONS)
    fun getTransactions(): Single<TransactionHistoryResponse>

    @POST(Endpoints.CREATE_TRANSACTION)
    fun createTransaction(@Body transactionRequest: TransactionRequest): Completable

    @GET(Endpoints.USER_INFO)
    fun getUserInfo(): Single<UserInfoResponse>

    @POST(Endpoints.FILTER_USERS)
    fun searchUsers(@Body filter: SearchUserRequest): Single<MutableList<UserInfoResponse.UserInfo>>
}