package com.nbcc.assignment1


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.nbcc.assignment1.databinding.FragmentGameBinding
import com.nbcc.quiz.Question

const val KEY_QUESTION_INDEX = "QuestionIndex"

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {
    private val questionBank = listOf(
        Question(R.string.question_1, false),
        Question(R.string.question_2, true),
        Question(R.string.question_3, true),
        Question(R.string.question_4, false),
        Question(R.string.question_5, false),
        Question(R.string.question_6, true),
        Question(R.string.question_7, false),
        Question(R.string.question_8, true),
        Question(R.string.question_9, false),
        Question(R.string.question_10, false),
        Question(R.string.question_11, false),
        Question(R.string.question_12, true),
        Question(R.string.question_13, false),
        Question(R.string.question_14, true),
        Question(R.string.question_15, false),
        Question(R.string.question_16, false),
        Question(R.string.question_17, true),
        Question(R.string.question_18, false),
        Question(R.string.question_19, false),
        Question(R.string.question_20, true)
    )

    private var questionIndex = 0

    private lateinit var correctToast : Toast
    private lateinit var incorrectToast : Toast

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Getting Question Index from Arguments
        questionIndex = GameFragmentArgs.fromBundle(arguments!!).questionIndex

        //Restoring Question Index on Rotate, etc.
        if (savedInstanceState != null) {
            questionIndex = savedInstanceState.getInt(KEY_QUESTION_INDEX)
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        correctToast = Toast.makeText(activity, "Correct!", Toast.LENGTH_SHORT)
        incorrectToast = Toast.makeText(activity, "Incorrect!", Toast.LENGTH_SHORT)

        binding.questionView.setText(questionBank[questionIndex].textResID)

        binding.btnTrue.setOnClickListener {
            if(questionBank[questionIndex].answer) {
                //Indicate that the user was correct with a toast
                correctToast.show()
            } else {
                //Indicate that the user was incorrect with a toast
                incorrectToast.show()
            }
        }

        binding.btnFalse.setOnClickListener {
            if(!questionBank[questionIndex].answer) {
                //Indicate that the user was correct with a toast
                correctToast.show()
            } else {
                //Indicate that the user was incorrect with a toast
                incorrectToast.show()
            }
        }

        binding.btnNext.setOnClickListener {
            questionIndex = (questionIndex + 1) % 20
            binding.questionView.setText(questionBank[questionIndex].textResID)
        }

        binding.btnBack.setOnClickListener {
            questionIndex = if (questionIndex != 0) { questionIndex - 1 } else { 19 }
            binding.questionView.setText(questionBank[questionIndex].textResID)
        }

        binding.btnCheat.setOnClickListener {
            view?.findNavController()?.navigate(GameFragmentDirections.actionGameFragmentToCheatFragment(questionIndex))
        }

        //Setting Up Options Menu
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        //Inflating the Options Menu
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!, view!!.findNavController()) || super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_QUESTION_INDEX, questionIndex)
        super.onSaveInstanceState(outState)
    }
}
