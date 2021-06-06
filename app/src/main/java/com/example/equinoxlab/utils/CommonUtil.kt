import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import com.example.equinoxlab.R

class CommonUtil(var context: Context) {
    private lateinit var progressDialog: ProgressDialog

    fun showProgressDialog() {
        progressDialog = ProgressDialog.show(
            context,
            context.resources.getString(R.string.loading_txt),
            context.resources.getString(R.string.please_wait_txt),
            true
        )
        progressDialog.show()
    }

    fun dismissProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

    val isNetworkConnected: Boolean get() {
        val ConnectionManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = ConnectionManager.activeNetworkInfo
        return if (networkInfo != null && networkInfo.isConnected == true) true else false
    }

}