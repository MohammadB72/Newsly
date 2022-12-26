package app.newsly.core.network.model

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmptyResponseConvertor @Inject constructor() : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, Any> {
        val delegate: Converter<ResponseBody, Any> =
            retrofit.nextResponseBodyConverter(this, type, annotations)
        return Converter<ResponseBody, Any> { body ->
            val contentType = body.contentType()
            if (contentType == null)
                EmptyResponseApiModel()
            else delegate.convert(
                body
            )
        }
    }
}
