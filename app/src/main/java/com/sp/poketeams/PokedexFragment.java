package com.sp.poketeams;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PokedexFragment extends Fragment {

    ArrayList<String> userListn = new ArrayList<String>();
    ArrayList<String> userListt = new ArrayList<String>();
    Handler mainHandler =  new Handler();
    ProgressDialog progressDialog;
    Customadapter customadapter;

    RecyclerView pokeview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pokedex,container,false);

        pokeview = v.findViewById(R.id.userList);

        customadapter = new Customadapter(getContext(),userListn,userListt);
        pokeview.setAdapter(customadapter);
        pokeview.setLayoutManager(new LinearLayoutManager(getContext()));

        new fetchData().start();

        return v;
    }

    class fetchData extends Thread {

        String data = "";


        @Override
        public void run(){

            mainHandler.post(new Runnable() {
                @Override
                public void run() {

                    /*progressDialog = new ProgressDialog(getContext());
                    progressDialog.setMessage("Fetching Data");
                    progressDialog.setCancelable(false);
                    progressDialog.show();*/

                }
            });

            try {
                URL url = new URL("https://pokeapi.co/api/v2/pokemon/zekrom");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while((line = bufferedReader.readLine()) != null){
                    data = data + line;
                }

                if (!data.isEmpty()){
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray users = jsonObject.getJSONArray("forms");
                    userListn.clear();
                    for(int  i =0;i< users.length();i++){
                        JSONObject names = users.getJSONObject(i);
                        String name = names.getString("name");
                        Log.d("ACT1", name);
                        userListn.add(name);
                    }

                    JSONArray typesj = jsonObject.getJSONArray("type");
                    userListt.clear();
                    for(int  i =0;i< typesj.length();i++){
                        JSONObject types = typesj.getJSONObject(i);
                        String type = types.getString("name");
                        Log.d("ACT1", type);
                        userListt.add(type);

                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mainHandler.post(new Runnable() {
                @Override
                public void run() {


                    customadapter = new Customadapter(getContext(),userListn,userListt);
                    pokeview.setAdapter(customadapter);
                    pokeview.setLayoutManager(new LinearLayoutManager(getContext()));


                    customadapter.notifyDataSetChanged();
                }
            });
        }
    }
}
