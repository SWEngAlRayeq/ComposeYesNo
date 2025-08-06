package app.yes_no.di

import app.yes_no.data.api.YesNoApi
import app.yes_no.data.repo_impl.YesNoRepositoryImpl
import app.yes_no.domain.repo.YesNoRepository
import app.yes_no.domain.usecase.GetYesNoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideOkHttpClient(logging: HttpLoggingInterceptor) =
        OkHttpClient.Builder().addInterceptor(logging).build()

    @Provides
    fun provideYesNoApi(client: OkHttpClient): YesNoApi =
        Retrofit.Builder()
            .baseUrl("https://yesno.wtf/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YesNoApi::class.java)

    @Provides
    fun provideRepository(api: YesNoApi): YesNoRepository =
        YesNoRepositoryImpl(api)

    @Provides
    fun provideUseCase(repository: YesNoRepository): GetYesNoUseCase =
        GetYesNoUseCase(repository)



}