package com.mctech.library.networking

/**
 * @author MAYCON CARDOSO on 2019-09-28.
 */
sealed class NetworkError : Throwable() {
    object ClientException          : NetworkError()
    object RemoteException          : NetworkError()

    object HostUnreachable          : NetworkError()
    object OperationTimeout         : NetworkError()
    object ConnectionSpike          : NetworkError()
    object UnknownNetworkingError   : NetworkError()

    override fun toString() =
        when (this) {
            ClientException         -> "Issue originated from client"
            RemoteException         -> "Issue incoming from server"

            HostUnreachable         -> "Cannot reach remote host"
            OperationTimeout        -> "Networking operation timed out"
            ConnectionSpike         -> "In-flight networking operation interrupted"
            UnknownNetworkingError  -> "Fatal networking exception"
        }
}