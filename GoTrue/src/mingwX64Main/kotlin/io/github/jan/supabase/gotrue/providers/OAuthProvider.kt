package io.github.jan.supabase.gotrue.providers

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.user.UserSession
import kotlinx.cinterop.ExperimentalForeignApi
import platform.windows.SW_SHOWNORMAL
import platform.windows.ShellExecuteW

/**
 * Represents an OAuth provider.
 */
@OptIn(ExperimentalForeignApi::class)
actual abstract class OAuthProvider actual constructor() : AuthProvider<ExternalAuthConfig, Unit> {
    /**
     * The name of the provider.
     */
    actual abstract val name: String

    actual override suspend fun login(
        supabaseClient: SupabaseClient,
        onSuccess: suspend (UserSession) -> Unit,
        redirectUrl: String?,
        config: (ExternalAuthConfig.() -> Unit)?
    ) {
        val externalConfig = ExternalAuthConfig().apply { config?.invoke(this) }
        val url = supabaseClient.gotrue.oAuthUrl(this@OAuthProvider, redirectUrl) {
            scopes.addAll(externalConfig.scopes)
            queryParams.putAll(externalConfig.queryParams)
        }
        ShellExecuteW(null, "open", url, null, null, SW_SHOWNORMAL);
    }

    actual override suspend fun signUp(
        supabaseClient: SupabaseClient,
        onSuccess: suspend (UserSession) -> Unit,
        redirectUrl: String?,
        config: (ExternalAuthConfig.() -> Unit)?
    ) = login(supabaseClient, onSuccess, redirectUrl, config = config)

    actual companion object

}