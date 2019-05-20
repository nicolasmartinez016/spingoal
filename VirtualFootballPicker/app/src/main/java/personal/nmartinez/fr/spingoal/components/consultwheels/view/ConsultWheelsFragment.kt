package personal.nmartinez.fr.spingoal.components.consultwheels.view


import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import personal.nmartinez.fr.spingoal.utils.AlertUtils
import personal.nmartinez.fr.spingoal.navigation.HideShowIconInterface
import personal.nmartinez.fr.spingoal.navigation.NavigationManager
import personal.nmartinez.fr.spingoal.R
import personal.nmartinez.fr.spingoal.components.consultwheels.adapters.ConsultWheelAdapter
import personal.nmartinez.fr.spingoal.components.consultwheels.presenter.ConsultWheelsPresenterImpl
import personal.nmartinez.fr.spingoal.components.consultwheels.presenter.ConsultWheelsPresenter
import personal.nmartinez.fr.spingoal.data.models.Wheel

import kotlinx.android.synthetic.main.fragment_consult_wheels.*

/**
 * A simple [Fragment] subclass.
 */
class ConsultWheelsFragment : Fragment(), ConsultWheelsView {

    private var presenter: ConsultWheelsPresenter? = null
    private lateinit var lifecycleRegistry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ConsultWheelsPresenterImpl(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        this.lifecycleRegistry = LifecycleRegistry(this)
        this.lifecycleRegistry.markState(Lifecycle.State.CREATED)
        val view = inflater?.inflate(R.layout.fragment_consult_wheels, container, false)

        (activity as HideShowIconInterface).showHamburgerIcon()
        if (arguments != null && arguments.getBoolean(FROM_WHEEL_CREATED_KEY)) {
            val message = if (arguments.getBoolean(IS_EDITING_KEY)) "La roue a été modifiée." else "La roue a été créée."
            AlertUtils.displaySuccessCrouton(activity, message, fl_consult_wheels)
            arguments.putBoolean(FROM_WHEEL_CREATED_KEY, false)
        }
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(view?.context)
        rv_consult_wheels?.layoutManager = layoutManager
        rv_consult_wheels?.setHasFixedSize(true)
        add_wheel_fab?.setOnClickListener { view1 -> NavigationManager.getInstance().createWheel(null) }
        presenter?.getData()
    }

    override fun showError(message: String) {

    }

    override fun showWheels(wheels: List<Wheel>?, favoriteWheelid: Int) {
        if (!wheels.isNullOrEmpty()) {
            pb_consult_wheels?.visibility = View.GONE
            tv_consult_wheels_waiting_for_data?.visibility = View.GONE
            rv_consult_wheels?.visibility = View.VISIBLE
            val adapter = ConsultWheelAdapter(wheels, activity, presenter, favoriteWheelid)
            rv_consult_wheels?.adapter = adapter
        }
    }

    override fun showFavoriteWheelChangedSuccess() {
        AlertUtils.displaySuccessCrouton(activity, "La roue par défaut a été modifiée", fl_consult_wheels)
    }

    override fun showFavoriteWheelChangedFailure() {
        AlertUtils.displayErrorCrouton(activity, "La roue par défaut n'a pas été modifiée", fl_consult_wheels)
    }

    override fun showWheelRemovedSuccess() {
        AlertUtils.displaySuccessCrouton(activity, "La roue a été supprimée", fl_consult_wheels)
    }

    override fun showWheelRemovedFailure() {
        AlertUtils.displayErrorCrouton(activity, "La roue n'a pas été supprimée. Vérifiez qu'il ne s'agit pas de la roue par défaut.", fl_consult_wheels)
    }

    override fun onResume() {
        super.onResume()
        lifecycleRegistry.markState(Lifecycle.State.RESUMED)

    }

    override fun onDestroy() {
        super.onDestroy()
        if (lifecycleRegistry != null) {
            lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
        }
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    companion object {

        val TAG = ConsultWheelsFragment::class.java.name
        private val FROM_WHEEL_CREATED_KEY = "FROM_WHEEL_CREATED_KEY"
        private val IS_EDITING_KEY = "IS_EDITING_KEY"

        fun newInstance(fromWheelCreated: Boolean, isEditing: Boolean): ConsultWheelsFragment {
            val fragment = ConsultWheelsFragment()
            val bundle = Bundle()
            bundle.putBoolean(FROM_WHEEL_CREATED_KEY, fromWheelCreated)
            bundle.putBoolean(IS_EDITING_KEY, isEditing)
            fragment.arguments = bundle
            return fragment
        }
    }
}
