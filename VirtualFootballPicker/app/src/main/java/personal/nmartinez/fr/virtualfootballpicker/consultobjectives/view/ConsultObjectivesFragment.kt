package personal.nmartinez.fr.virtualfootballpicker.consultobjectives.view

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.fragment_consult_objectives.*
import personal.nmartinez.fr.virtualfootballpicker.AlertUtils
import personal.nmartinez.fr.virtualfootballpicker.HideShowIconInterface
import personal.nmartinez.fr.virtualfootballpicker.NavigationManager
import personal.nmartinez.fr.virtualfootballpicker.R
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.adapters.ConsultObjectivesAdapter
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.core.ConsultObjectivesPresenter
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.core.ConsultObjectivesPresenterImpl
import personal.nmartinez.fr.virtualfootballpicker.models.Objective

/**
 * A simple [Fragment] subclass.
 */
class ConsultObjectivesFragment : Fragment(), ConsultObjectivesView {

    private var presenter: ConsultObjectivesPresenter? = null
    private lateinit var lifecycleRegistry: LifecycleRegistry
    private lateinit var adapter: ConsultObjectivesAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        this.lifecycleRegistry = LifecycleRegistry(this)
        this.lifecycleRegistry.markState(Lifecycle.State.CREATED)
        this.presenter = ConsultObjectivesPresenterImpl(activity, this)

        val view = inflater?.inflate(R.layout.fragment_consult_objectives, container, false)


        if (arguments != null && arguments.getBoolean(FROM_OBJECTIVE_CREATED_KEY)) {
            val message = if (arguments.getBoolean(IS_EDITING_KEY)) "Le gage a été modifié." else "Le gage a été créé."
            AlertUtils.displaySuccessCrouton(activity, message, rl_consult_objectives_layout)
        }
        (activity as HideShowIconInterface).showHamburgerIcon()

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.presenter?.getData()
        val layoutManager = LinearLayoutManager(view?.context)
        consult_objectives_recycler_view?.layoutManager = layoutManager
        consult_objectives_recycler_view?.setHasFixedSize(true)

        create_objective_fab?.setOnClickListener { view1 -> NavigationManager.getInstance().createObjective() }
    }

    override fun displayWaitingForData(wait: Boolean) {
        if (wait) {
            consult_objectives_recycler_view?.visibility = View.GONE
            consult_objetives_progressbar?.visibility = View.VISIBLE
            consult_objetives_waiting_message?.visibility = View.VISIBLE
        } else {
            consult_objectives_recycler_view?.visibility = View.VISIBLE
            consult_objetives_progressbar?.visibility = View.GONE
            consult_objetives_waiting_message?.visibility = View.GONE
        }
    }

    override fun showObjectives(objectives: List<Objective>) {
        this.adapter = ConsultObjectivesAdapter(objectives, this.presenter, activity)
        consult_objectives_recycler_view?.adapter = adapter
        displayWaitingForData(false)
    }

    override fun showObjectiveDeletedSuccess() {
        AlertUtils.displaySuccessCrouton(activity, "Le gage a été supprimé", rl_consult_objectives_layout)
    }

    override fun showObjectiveDeletedFailure() {
        AlertUtils.displayErrorCrouton(activity, "Le gage n'a pas été supprimé", rl_consult_objectives_layout)
    }

    override fun onResume() {
        super.onResume()
        this.lifecycleRegistry.markState(Lifecycle.State.RESUMED)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    companion object {

        val TAG = ConsultObjectivesFragment::class.java.name

        private val FROM_OBJECTIVE_CREATED_KEY = "FROM_OBJECTIVE_CREATED_KEY"
        private val IS_EDITING_KEY = "IS_EDITING_KEY"

        fun newInstance(fromObjectiveCreated: Boolean, isEditing: Boolean): ConsultObjectivesFragment {
            val fragment = ConsultObjectivesFragment()
            val args = Bundle()
            args.putBoolean(FROM_OBJECTIVE_CREATED_KEY, fromObjectiveCreated)
            args.putBoolean(IS_EDITING_KEY, isEditing)
            fragment.arguments = args
            return fragment
        }
    }
}
