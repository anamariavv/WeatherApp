package model.common

typealias EmptyResource = Resource<Unit>

sealed class Resource<T>(val data: T? = null, val errorData: ErrorData? = null) {

    class Success<T>(data: T) : Resource<T>(data = data) {
        companion object {
            fun empty(): Resource<Unit> = Success(Unit)
        }
    }

    class Error<T> : Resource<T> {
        constructor(errorData: ErrorData) : super(errorData = errorData)
        constructor(
            errorType: ErrorType,
            throwable: Throwable
        ) : this(ErrorData(errorType = errorType, throwable = throwable))
    }

    suspend fun onFinished(
        successCallback: suspend (T) -> Unit,
        errorCallback: (ErrorData) -> Unit
    ) {
        when {
            data != null -> successCallback(data)
            errorData != null -> errorCallback(errorData)
        }
    }

    suspend fun onFinished(
        successCallback: suspend () -> Unit,
        errorCallback: (ErrorData) -> Unit
    ) {
        onFinished(successCallback = { _ -> successCallback() }, errorCallback = errorCallback)
    }

    suspend fun onSuccess(successCallback: suspend (T) -> Unit) {
        if (data != null) {
            successCallback(data)
        }
    }

    suspend fun onError(errorCallback: (ErrorData) -> Unit) {
        if(errorData != null) {
            errorCallback(errorData)
        }
    }
}