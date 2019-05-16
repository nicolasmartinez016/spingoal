package personal.nmartinez.fr.virtualfootballpicker.splashscreen.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_splash_screen.*
import personal.nmartinez.fr.virtualfootballpicker.MainActivity
import personal.nmartinez.fr.virtualfootballpicker.R
import personal.nmartinez.fr.virtualfootballpicker.splashscreen.presenter.SplashScreenPresenter
import personal.nmartinez.fr.virtualfootballpicker.splashscreen.presenter.SplashScreenPresenterImpl

class SplashScreenActivity : AppCompatActivity(), SplashScreenView {

    private lateinit var presenter: SplashScreenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        this.presenter = SplashScreenPresenterImpl(this, this, R.raw.default_wheel)
        this.presenter.initApp()
    }

    override fun showCreatingWheel() {
        tv_splash_screen_status.text = resources.getString(R.string.splash_screen_initializing_data)
    }

    override fun showErrorInObjectivesCreation() {
        tv_splash_screen_status.text = resources.getString(R.string.splash_screen_error_creating_objectives)
    }

    override fun showWheelCreated() {
        tv_splash_screen_status.text = resources.getString(R.string.splash_screen_data_created)
        startMainActivity()
    }

    override fun showErrorInWheelCreation() {
        tv_splash_screen_status.text = resources.getString(R.string.splash_screen_error_creating_wheel)
    }

    override fun showWheelsRetrieved() {
        tv_splash_screen_status.text = resources.getString(R.string.splash_screen_data_retrieved)
        startMainActivity()
    }

    override fun showCreatingObjectives() {
        tv_splash_screen_status.text = resources.getString(R.string.splash_screen_creating_objectives)
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
