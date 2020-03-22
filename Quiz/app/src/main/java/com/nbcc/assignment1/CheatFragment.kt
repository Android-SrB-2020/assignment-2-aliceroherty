package com.nbcc.assignment1


import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.nbcc.assignment1.databinding.FragmentCheatBinding
import com.nbcc.quiz.Question

class CheatFragment : Fragment() {
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

    private lateinit var binding: FragmentCheatBinding

    private var questionIndex: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cheat, container, false)
        //Getting Question Index from Arguments
        questionIndex = CheatFragmentArgs.fromBundle(arguments!!).questionIndex

        //Restoring Question Index on Rotate, etc.
        if (savedInstanceState != null) {
            questionIndex = savedInstanceState.getInt(KEY_QUESTION_INDEX)
        }

        //Setting Question Text
        binding.questionText.setText(questionBank[questionIndex].textResID)

        //Setting Answer Text
        binding.answerText.text = if (questionBank[questionIndex].answer) {"True"} else {"False"}

        //Setting Up Event-Handlers
        binding.btnCancel.setOnClickListener {
            view?.findNavController()?.navigate(CheatFragmentDirections.actionCheatFragmentToGameFragment(questionIndex))
        }

        binding.btnCheat.setOnClickListener {
            binding.answerText.visibility = View.VISIBLE
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_QUESTION_INDEX, questionIndex)
        super.onSaveInstanceState(outState)
    }

}
