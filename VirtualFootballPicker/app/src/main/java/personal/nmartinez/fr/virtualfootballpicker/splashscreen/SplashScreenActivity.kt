package personal.nmartinez.fr.virtualfootballpicker.splashscreen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_splash_screen.*
import personal.nmartinez.fr.virtualfootballpicker.MainActivity
import personal.nmartinez.fr.virtualfootballpicker.R

class SplashScreenActivity : AppCompatActivity(), SplashScreenView {

    private lateinit var presenter: SplashScreenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        this.presenter = SplashScreenPresenterImpl(this, this, R.raw.default_wheel)
        this.presenter.initApp()
    }

    override fun showCreatingWheel() {
        tv_splash_screen_status.text = "Initialisation de la roue principale..."
    }

    override fun showErrorInObjectivesCreation() {
        tv_splash_screen_status.text = "Une erreur est survenue lors de la creation des gages..."
    }

    override fun showWheelCreated() {
        tv_splash_screen_status.text = "La roue principale a été créée, l'application va démarrer..."
        startMainActivity()
    }

    override fun showErrorInWheelCreation() {
        tv_splash_screen_status.text = "Une erreur est survenue lors de la creation de la roue..."
    }

    override fun showWheelsRetrieved() {
        tv_splash_screen_status.text = "Les données ont été récupérées, l'application va démarrer..."
        startMainActivity()
    }

    override fun showCreatingObjectives() {
        tv_splash_screen_status.text = "Initialisation des gages..."
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
