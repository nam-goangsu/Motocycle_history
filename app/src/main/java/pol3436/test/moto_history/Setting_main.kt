package pol3436.test.moto_history

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class Setting_main : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}