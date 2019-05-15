package personal.nmartinez.fr.virtualfootballpicker

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.WindowManager
import com.google.gson.Gson
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.ObjectiveRepositoryImpl
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.interfaces.CreateObjectiveRepositoryListener
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.interfaces.ObjectiveRepository
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.wheel.WheelRepositoryImpl
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.wheel.interfaces.CreateWheelRepositoryListener
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.wheel.interfaces.WheelRepository


import java.util.ArrayList

import personal.nmartinez.fr.virtualfootballpicker.models.Wheel
import personal.nmartinez.fr.virtualfootballpicker.models.Objective
import personal.nmartinez.fr.virtualfootballpicker.models.WheelObjectiveJoin

open class MainActivity : AppCompatActivity(), HideShowIconInterface, CreateWheelRepositoryListener, CreateObjectiveRepositoryListener {

    private lateinit var toggle: ActionBarDrawerToggle
    private var drawer: DrawerLayout? = null
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        window.setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED)

        drawer = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer!!.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        wheelRepository = WheelRepositoryImpl()
        objectiveRepository = ObjectiveRepositoryImpl()
        initObjectives()
        bottomNavigationView = findViewById<View>(R.id.navigation) as BottomNavigationView
        NavigationManager.getInstance().init(supportFragmentManager, this, drawer, navigationView, bottomNavigationView)

        NavigationManager.getInstance().homePage()
    }

    override fun onBackPressed() {
        if (drawer != null && drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }



    override fun showHamburgerIcon() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        toggle.isDrawerIndicatorEnabled = true
    }

    override fun showBackIcon() {
        toggle.isDrawerIndicatorEnabled = false
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toggle.toolbarNavigationClickListener = View.OnClickListener { onBackPressed() }
    }

    private fun initObjectives() {
//        val over65percent = Objective.ObjectiveBuilder().id(1).isEditable(false).period(Objective.FIRST_PERIOD).name("Gagner avec > 65% poss").description("").build()
//        val under65percent = Objective.ObjectiveBuilder().id(2).isEditable(false).period(Objective.FIRST_PERIOD).name("Gagner avec < 35% poss").description("").build()
//        val scoreHatTrick = Objective.ObjectiveBuilder().id(3).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer un doublé").description("").build()
//        val noRond = Objective.ObjectiveBuilder().id(4).isEditable(false).period(Objective.BOTH_PERIODS).name("Gagner sans la touche rond").description("").build()
//        val noCroix = Objective.ObjectiveBuilder().id(5).isEditable(false).period(Objective.BOTH_PERIODS).name("Gagner sans la touche croix").description("").build()
//        val noTriangle = Objective.ObjectiveBuilder().id(6).isEditable(false).period(Objective.BOTH_PERIODS).name("Gagner sans la touche triangle").description("").build()
//        val noCarre = Objective.ObjectiveBuilder().id(7).isEditable(false).period(Objective.BOTH_PERIODS).name("Gagner sans la touche carré").description("").build()
//        val scoreTwoVolleys = Objective.ObjectiveBuilder().id(8).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer 1 but de volée").description("").build()
//        val scoreTwoHeaders = Objective.ObjectiveBuilder().id(9).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer 1 but de la tête").description("").build()
//        val score2pens = Objective.ObjectiveBuilder().id(10).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer 1 penalty").description("").build()
//        val stop1pen = Objective.ObjectiveBuilder().id(11).isEditable(false).period(Objective.BOTH_PERIODS).name("Arreter 1 penalty").description("").build()
//        val fifteenPassesBeforeScoring = Objective.ObjectiveBuilder().id(12).isEditable(false).period(Objective.BOTH_PERIODS).name("15 passes dans le camp adverse avant de marquer").description("").build()
//        val noR2 = Objective.ObjectiveBuilder().id(13).isEditable(false).period(Objective.BOTH_PERIODS).name("Gagner sans la touche R2").description("").build()
//        val noL2 = Objective.ObjectiveBuilder().id(14).isEditable(false).period(Objective.BOTH_PERIODS).name("Gagner sans la touche L2").description("").build()
//        val noR1 = Objective.ObjectiveBuilder().id(15).isEditable(false).period(Objective.BOTH_PERIODS).name("Gagner sans la touche R1").description("").build()
//        val noL1 = Objective.ObjectiveBuilder().id(16).isEditable(false).period(Objective.BOTH_PERIODS).name("Gagner sans la touche L1").description("").build()
//        val get4corners = Objective.ObjectiveBuilder().id(17).isEditable(false).period(Objective.BOTH_PERIODS).name("Obtenir 4 corners").description("").build()
//        val scoreDirectFk = Objective.ObjectiveBuilder().id(18).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer sur coup franc direct").description("").build()
//        val noFoul = Objective.ObjectiveBuilder().id(19).isEditable(false).period(Objective.BOTH_PERIODS).name("Faire 0 faute en taclant au moins 3 fois").description("").build()
//        val scoreFirst15 = Objective.ObjectiveBuilder().id(20).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer dans les 15 premieres minutes").description("").build()
//        val scoreLast15 = Objective.ObjectiveBuilder().id(21).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer dans les 15 dernieres minutes").description("").build()
//        val scoreDefender = Objective.ObjectiveBuilder().id(22).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer avec un defenseur (placé defenseur)").description("").build()
//        val dribbleGoal = Objective.ObjectiveBuilder().id(23).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer en eliminant le gardien").description("").build()
//        val screamerTwice = Objective.ObjectiveBuilder().id(24).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer de l'exterieur de la surface").description("").build()
//        val wrongFootTwice = Objective.ObjectiveBuilder().id(25).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer du mauvais pied").description("").build()
//        val stoppedPenalty = Objective.ObjectiveBuilder().id(26).isEditable(false).period(Objective.BOTH_PERIODS).name("Se faire arreter un penalty").description("").build()
//        val conceide0shot = Objective.ObjectiveBuilder().id(27).isEditable(false).period(Objective.BOTH_PERIODS).name("Ne concéder aucun tir").description("").build()
//        val scoreLessThan5Passes = Objective.ObjectiveBuilder().id(28).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer en moins de 5 passes").description("").build()
//        val oneTouchFromOtherHalf = Objective.ObjectiveBuilder().id(29).isEditable(false).period(Objective.BOTH_PERIODS).name("Marquer avec action en une touche de balle a partir du camp adverse").description("").build()
//        val provokeOwnGoal = Objective.ObjectiveBuilder().id(30).isEditable(false).period(Objective.BOTH_PERIODS).name("Provoquer un but contre son camp").description("").build()
//
//        val objectives = ArrayList<Objective>()
//        objectives.add(over65percent)
//        objectives.add(under65percent)
//        objectives.add(scoreHatTrick)
//        objectives.add(noRond)
//        objectives.add(noCroix)
//        objectives.add(noTriangle)
//        objectives.add(noCarre)
//        objectives.add(scoreTwoVolleys)
//        objectives.add(scoreTwoHeaders)
//        objectives.add(score2pens)
//        objectives.add(stop1pen)
//        objectives.add(fifteenPassesBeforeScoring)
//        objectives.add(noR2)
//        objectives.add(noL2)
//        objectives.add(noR1)
//        objectives.add(noL1)
//        objectives.add(get4corners)
//        objectives.add(scoreDirectFk)
//        objectives.add(noFoul)
//        objectives.add(scoreFirst15)
//        objectives.add(scoreLast15)
//        objectives.add(scoreDefender)
//        objectives.add(dribbleGoal)
//        objectives.add(screamerTwice)
//        objectives.add(wrongFootTwice)
//        objectives.add(stoppedPenalty)
//        objectives.add(conceide0shot)
//        objectives.add(scoreLessThan5Passes)
//        objectives.add(provokeOwnGoal)
//        objectives.add(oneTouchFromOtherHalf)
//
//        val wheel = Wheel()
//        wheel.id = 1
//        wheel.name = "Roue de base"
//        wheel.objectives = objectives
//        wheel.creator = "admin"
//        wheel.isEditable = false
//
//        val wheels = ArrayList<Wheel>()
//        wheels.add(wheel)



    }

    override fun onCreateWheelSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateWheelFailure() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateObjectiveSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateObjectiveFailure() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


