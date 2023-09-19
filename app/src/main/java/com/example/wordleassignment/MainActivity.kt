package com.example.wordleassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import FourLetterWordList
import android.content.Context
import android.text.Editable
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {
    private val wordToGuess = FourLetterWordList.getRandomFourLetterWord()
    private var currentAttempt = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val inputButton = findViewById<Button>(R.id.inputButton)
        val hiddenWordView = findViewById<TextView>(R.id.wordToGuess)
        val gameResultView = findViewById<TextView>(R.id.gameResultView)
        hiddenWordView.text = wordToGuess

        fun checkGuess(guess: Editable) : String {
            var result = ""
            for (i in 0..3) {
                if (guess[i] == wordToGuess[i]) {
                    result += "O"
                }
                else if (guess[i] in wordToGuess) {
                    result += "+"
                }
                else {
                    result += "X"
                }
            }
            return result
        }

        fun hideKeyboard() {
            val view = this.currentFocus // Get the currently focused view
            if (view != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        val inputText = findViewById<EditText>(com.example.wordleassignment.R.id.inputText)     // Grab input text from the EditText view
        val guess1TextView = findViewById<TextView>(R.id.Guess1Entry)                           // Display the entered text
        val guess1TextResultView = findViewById<TextView>(R.id.Guess1Result)                    // Create
        val guess1TextCheckView = findViewById<TextView>(R.id.Guess1Check)                      // Create variable for the first check/result

        val guess2View = findViewById<TextView>(R.id.Guess2)
        val guess2TextView = findViewById<TextView>(R.id.Guess2Entry)
        val guess2TextResultView = findViewById<TextView>(R.id.Guess2Result)
        val guess2TextCheckView = findViewById<TextView>(R.id.Guess2Check)

        val guess3View = findViewById<TextView>(R.id.Guess3)
        val guess3TextView = findViewById<TextView>(R.id.Guess3Entry)
        val guess3TextResultView = findViewById<TextView>(R.id.Guess3Result)
        val guess3TextCheckView = findViewById<TextView>(R.id.Guess3Check)

        var currentAttempt = 1

        inputButton.setOnClickListener {
            if (currentAttempt <= 3) {
                hideKeyboard()
                val userInput = inputText.text.toString()
                val result = checkGuess(inputText.text)

                when (currentAttempt) {
                    1 -> {
                        guess1TextView.text = userInput
                        guess1TextView.visibility = View.VISIBLE
                        guess1TextResultView.visibility = View.VISIBLE
                        guess1TextCheckView.text = result
                        guess1TextCheckView.visibility = View.VISIBLE
                    }
                    2 -> {
                        guess2View.visibility = View.VISIBLE
                        guess2TextView.text = userInput
                        guess2TextView.visibility = View.VISIBLE
                        guess2TextResultView.visibility = View.VISIBLE
                        guess2TextCheckView.text = result
                        guess2TextCheckView.visibility = View.VISIBLE
                    }
                    3 -> {
                        guess3View.visibility = View.VISIBLE
                        guess3TextView.text = userInput
                        guess3TextView.visibility = View.VISIBLE
                        guess3TextResultView.visibility = View.VISIBLE
                        guess3TextResultView.text = result
                        guess3TextCheckView.visibility = View.VISIBLE
                    }
                }

                if (result == "OOOO") {
                    gameResultView.text = "YOU WIN!"
                    gameResultView.visibility = View.VISIBLE
                }
                else if(result != "OOOO" && currentAttempt >= 3){
                    gameResultView.text = "YOU LOSE!"
                    gameResultView.visibility = View.VISIBLE
                    inputText.text.clear()
                }
                currentAttempt++

                if (currentAttempt <= 3) {
                    inputText.text.clear() // Clear the input for the next attempt
                    inputButton.isEnabled = true // Enable the button for the next attempt
                } else {
                    inputText.isEnabled = false
                    inputButton.isEnabled = false // Disable the button after reaching the maximum attempts
                    hiddenWordView.visibility = View.VISIBLE
                }
            } else {
                gameResultView.text = "YOU LOSE!(2)"
                gameResultView.visibility = View.VISIBLE
            }
        }


    }

}