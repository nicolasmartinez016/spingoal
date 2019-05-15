package personal.nmartinez.fr.virtualfootballpicker.splashscreen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorInObjectivesCreation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showWheelCreated() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorInWheelCreation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showWheelsRetrieved() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showCreatingObjectives() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
