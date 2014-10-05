package com.sm.backpackingplanner;
//
//import android.os.Bundle;
//import android.text.TextUtils;
//
//import com.amazon.identity.auth.device.AuthError;
//import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
//import com.amazon.identity.auth.device.shared.APIListener;
//
//public class TokenListener implements APIListener {
//
//	/* getToken completed successfully. */
//	@Override
//	public void onSuccess(Bundle response) {
//		final String authzToken = response
//				.getString(AuthzConstants.BUNDLE_KEY.TOKEN.val);
//		if (!TextUtils.isEmpty(authzToken)) {
//			// Retrieve the profile data
//			mAuthManager.getProfile(new ProfileListener());
//		}
//	}
//
//	/* There was an error during the attempt to get the token. */
//	@Override
//	public void onError(AuthError ae) {
//	}
//}