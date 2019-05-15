package personal.nmartinez.fr.virtualfootballpicker.splashscreen

import android.arch.lifecycle.Observer
import android.content.Context
import com.google.gson.Gson
import personal.nmartinez.fr.virtualfootballpicker.FileManager
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.interfaces.CreateObjectiveRepositoryListener
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.interfaces.ObjectiveRepository
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.wheel.interfaces.CreateWheelRepositoryListener
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.wheel.interfaces.WheelRepository
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel

class SplashScreenPresenterImpl : SplashScreenPresenter, CreateObjectiveRepositoryListener, CreateWheelRepositoryListener {

    private var view: SplashScreenView
    private lateinit var wheelRepository: WheelRepository
    private lateinit var objectiveRepository: ObjectiveRepository
    private var context : Context
    private var wheel: Wheel? = null
    private var resourceId: Int = 0

    constructor(view: SplashScreenView, context: Context, resourceId: Int) {
        this.view = view
        this.context = context
        this.resourceId = resourceId
    }

    override fun initApp() {
        wheelRepository.wheels.observe(view, Observer<List<Wheel>> { wheelsRetrieved ->
            run {
                if (!wheelsRetrieved.isNullOrEmpty()) {
                    onGetWheelsSuccess()
                } else {
                    onGetWheelsFailure()
                }
            }
        })
    }

    override fun onCreateObjectiveSuccess() {
        view.showCreatingWheel()
        wheelRepository.createWheel(wheel, this)
    }

    override fun onCreateObjectiveFailure() {
        view.showErrorInObjectivesCreation()
    }

    override fun onCreateWheelSuccess() {
        view.showWheelCreated()
    }

    override fun onCreateWheelFailure() {
        view.showErrorInWheelCreation()
    }

    protected fun onGetWheelsSuccess() {
        view.showWheelsRetrieved()
    }

    protected fun onGetWheelsFailure() {
        initWheelAndObjectives()
    }

    private fun initWheelAndObjectives() {
        val json = FileManager.readJsonFile(context, resourceId)
        val gson = Gson()
        this.wheel = gson.fromJson(json, Wheel::class.java)
        view.showCreatingObjectives()
        objectiveRepository.createObjectives(wheel?.objectives, this)
    }
}