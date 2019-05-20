package personal.nmartinez.fr.spingoal.splashscreen.presenter

import android.arch.lifecycle.Observer
import android.content.Context
import com.google.gson.Gson
import personal.nmartinez.fr.spingoal.utils.FileUtils
import personal.nmartinez.fr.spingoal.data.repositories.objective.ObjectiveRepositoryImpl
import personal.nmartinez.fr.spingoal.data.repositories.objective.interfaces.CreateObjectiveRepositoryListener
import personal.nmartinez.fr.spingoal.data.repositories.objective.interfaces.ObjectiveRepository
import personal.nmartinez.fr.spingoal.data.repositories.wheel.WheelRepositoryImpl
import personal.nmartinez.fr.spingoal.data.repositories.wheel.interfaces.CreateWheelRepositoryListener
import personal.nmartinez.fr.spingoal.data.repositories.wheel.interfaces.WheelRepository
import personal.nmartinez.fr.spingoal.data.models.Wheel
import personal.nmartinez.fr.spingoal.splashscreen.view.SplashScreenView
import kotlin.concurrent.thread

class SplashScreenPresenterImpl : SplashScreenPresenter, CreateObjectiveRepositoryListener, CreateWheelRepositoryListener {

    private var view: SplashScreenView
    private var wheelRepository: WheelRepository
    private var objectiveRepository: ObjectiveRepository
    private var context : Context
    private var wheel: Wheel? = null
    private var resourceId: Int = 0

    constructor(view: SplashScreenView, context: Context, resourceId: Int) {
        this.view = view
        this.context = context
        this.resourceId = resourceId
        this.wheelRepository = WheelRepositoryImpl()
        this.objectiveRepository = ObjectiveRepositoryImpl()
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
        thread {
            val json = FileUtils.readJsonFile(context, resourceId)
            val gson = Gson()
            this.wheel = gson.fromJson(json, Wheel::class.java)
            view.showCreatingObjectives()
            objectiveRepository.createObjectives(wheel?.objectives, this)
        }
    }
}