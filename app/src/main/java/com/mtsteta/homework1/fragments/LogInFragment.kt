package com.mtsteta.homework1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.mtsteta.homework1.MyViewModel
import com.mtsteta.homework1.R

private const val USER_NAME: String = "userName"
private const val USER_PASSWORD: String = "userPassword"
private const val INVALID_PASSWORD_MESSAGE: String = "Неверный пароль! Попробуйте ещё раз!"

class LogInFragment() : Fragment() {

    private lateinit var userNameEditText: EditText
    private lateinit var userPasswordEditText: EditText
    private lateinit var userButton: Button
    private lateinit var navController: NavController

    private var userName: String = ""

    private val myViewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userNameEditText = view.findViewById(R.id.log_in_name_edit_text)
        userPasswordEditText = view.findViewById(R.id.log_in_password_edit_text)
        userButton = view.findViewById(R.id.log_in_user_button)
        navController = view.findNavController()

        userButton.setOnClickListener {
            userName = userNameEditText.text.toString()
            if (myViewModel.getValueByKeyInPrefs(USER_NAME) == "" ||
                    myViewModel.getValueByKeyInPrefs(USER_NAME) != userName) {
                myViewModel.addPairToPrefs(USER_NAME, userName)
                myViewModel.addPairToPrefs(USER_PASSWORD, userPasswordEditText.text.toString())
                userNameEditText.text.clear()
                userPasswordEditText.text.clear()
                navigateToMovieList()
            }
            else if (myViewModel.getValueByKeyInPrefs(USER_NAME) == userName) {
                if (userPasswordEditText.text.toString() !=
                        myViewModel.getValueByKeyInPrefs(USER_PASSWORD)) {
                    Toast.makeText(requireContext(),
                            INVALID_PASSWORD_MESSAGE, Toast.LENGTH_SHORT).show()
                }
                else {
                    userNameEditText.text.clear()
                    userPasswordEditText.text.clear()
                    navigateToMovieList()
                }
            }
        }
    }

    fun navigateToMovieList() {
        navController.navigate(R.id.action_logInFragment_to_movieListFragment)
    }

    companion object {
        @JvmStatic
        fun newInstance() = LogInFragment()
    }
}