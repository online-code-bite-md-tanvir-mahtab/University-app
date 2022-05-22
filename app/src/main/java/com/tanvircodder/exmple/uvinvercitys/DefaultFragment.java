package com.tanvircodder.exmple.uvinvercitys;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tanvircodder.exmple.uvinvercitys.databaes.DatabaseClient;
import com.tanvircodder.exmple.uvinvercitys.model.Util;
import com.tanvircodder.exmple.uvinvercitys.network.JsonParser;
import com.tanvircodder.exmple.uvinvercitys.network.UrlConnection;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;


public class DefaultFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Util>> {
    private RecyclerView mRecyclerView;
    private VersityAdapter mAdapter;

    public DefaultFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_default, container, false);
        // Inflate the layout for this fragment
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new VersityAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        getLoaderManager().initLoader(0,null,this);
        return view;
    }
    @NonNull
    @Override
    public Loader<List<Util>> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<List<Util>>(getContext()) {
            List<Util> mData = null;
            @Override
            protected void onStartLoading() {
                if (mData != null){
                    deliverResult(mData);
                }else{
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public List<Util> loadInBackground() {
                String defaultCountry = "Bangladesh";
                URL url = UrlConnection.buildUrl(defaultCountry);
                System.out.println("The url : " + url.toString());
                List<Util> parsingData = null;
                try {
                    if (isNetworkConnected()){
                        if (isInternetAvailable()){
                            String httpResponse = UrlConnection.HttpResponse(url);
                            parsingData = JsonParser.UrlJsonForParsing(getContext(),httpResponse);
                            System.out.println(parsingData);
                        }else{
//                            TODO: we are going to add something
                            Toast.makeText(getContext(),"The internet is ot connected",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        parsingData = DatabaseClient.getmInstance(getContext()).getAppDatabase()
                                .versityDao()
                                .getAll();
                    }

                    return parsingData;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            public void deliverResult(List<Util> data){
                mData = data;
                super.deliverResult(data);
            }
        };
    }



    @Override
    public void onLoadFinished(@NonNull Loader<List<Util>> loader, List<Util> data) {
        mAdapter.swapData(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Util>> loader) {
        mAdapter.swapData(null);
    }
    private boolean isNetworkConnected(){
        ConnectivityManager connectivityManager =(ConnectivityManager)  getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }



    private boolean isInternetAvailable(){
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return !address.equals("");
        } catch (UnknownHostException e) {
//            todo we will do something in here
        }
        return false;
    }

    public void refreshTheLoad(){
        getLoaderManager().initLoader(0,null,this);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.refresh_item,menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.refresh){
            refreshTheLoad();
            return true;
        }
        return false;
    }
}