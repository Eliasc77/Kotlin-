package com.example.ainterdent.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ainterdent.Preferencias.UserApplication
import com.example.ainterdent.R
import com.example.ainterdent.model.User
import com.example.ainterdent.view.LoginActivity
import com.example.ainterdent.viewmodel.AccountFragmentViewModel
import com.example.ainterdent.viewmodel.MenuFragmenViewModel
import kotlinx.android.synthetic.main.fragment_account.*


class AccountFragment : Fragment() {

    //google
    lateinit var viewModel : AccountFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(AccountFragmentViewModel::class.java)
        viewModel._user.observe(requireActivity(), Observer {
            txtapellido.setText("${it.apellidos}")
            txtdni.setText("${it.dni}")
            txtdireccion.setText("${it.direccion}")
            txtnombres.setText("${it.nombres}")
            txttelephone.setText("${it.telefono}")
            txtemail.setText("${it.email}")
        })
        viewModel.listar(UserApplication.prefs.getId())

        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSave.setOnClickListener{ updateData() }
        init()
    }

    fun init(){
        btnLogOut.setOnClickListener{
            UserApplication.prefs.wipe()
            startActivity(Intent(requireContext(),LoginActivity::class.java))
        }




    }

    private fun updateData() {
        val id = UserApplication.prefs.getId()
        val user = User(
            id,
            txtapellido.text.toString(),
            txtdireccion.text.toString(),
            null,
            txtdni.text.toString(),
            txtemail.text.toString(),
            txtnombres.text.toString(),
            UserApplication.prefs.getPasswd(),
            null,
            txttelephone.text.toString(),
            null
        )
        viewModel.update(id,user)
        Toast.makeText(requireContext(), "Usuario Actualizado ", Toast.LENGTH_LONG).show()
    }


}