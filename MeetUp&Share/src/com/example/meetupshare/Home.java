package com.example.meetupshare;


import org.apache.http.Header;

import com.example.models.User;
import com.example.webservice.Webservice;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Home extends Activity {

	User currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		//Recuperation des informations relatives a l'user
		if(getIntent() != null) {
			currentUser = (User)getIntent().getExtras().get("currentUser");
		}
	}

	public void contacts(View view){	 
		Intent intent = new Intent(Home.this, Contacts.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("currentUser", currentUser);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	public void calandar(View view){	 
		Intent intent = new Intent(Home.this, Calandar.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("currentUser", currentUser);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	public void nouvel_evenement(View view){	 
		Intent intent = new Intent(Home.this, Nouvel_evenement.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("currentUser", currentUser);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	/**
	 * Suppression du compte de l'utilisateur 
	 * @param view
	 */
	public void supprimerCompte(View view){
		String url = "?method=deleteuser&email="+currentUser.getEmail();
		Webservice.delete(url, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				Log.d("Delete account", "success");
				Toast toast = Toast.makeText(getApplicationContext(), "Compte supprim�", Toast.LENGTH_SHORT);
				toast.show();
				//passage a activity "Connexion"
				Intent intent = new Intent(getApplicationContext(),Connexion.class);	 
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 	 
				startActivity(intent);
				
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				Log.d("Delete account", "failure");
				Toast toast = Toast.makeText(getApplicationContext(), "Echec de la suppression", Toast.LENGTH_SHORT);
				toast.show();
				
			}			
		});
	}

}
