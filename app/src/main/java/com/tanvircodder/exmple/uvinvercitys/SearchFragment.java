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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tanvircodder.exmple.uvinvercitys.databaes.DatabaseClient;
import com.tanvircodder.exmple.uvinvercitys.model.Util;
import com.tanvircodder.exmple.uvinvercitys.network.JsonParser;
import com.tanvircodder.exmple.uvinvercitys.network.UrlConnection;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;


public class SearchFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Util>> {
    private RecyclerView mRecyclerView;
    private VersityAdapter mAdapter;
    private EditText mTextView;
    Button mSearchView;

    public SearchFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        getLoaderManager().initLoader(0,null,this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new VersityAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        mSearchView = (Button) view.findViewById(R.id.search);
        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLoaderManager().initLoader(0,null,SearchFragment.this);

            }
        });
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
                mTextView = (EditText) getActivity().findViewById(R.id.search_text_view);
                String country_name = mTextView.getText().toString();
                if (country_name.isEmpty()){
                    return null;
                }else{
                    String defaultCountry = country_name;
                    URL url = UrlConnection.buildUrl(defaultCountry);
                    List<Util> parsingData = null;
                    try {
                        if (isNetworkConnected()){
                            if (isInternetAvailable()){
                                String httpResponse = UrlConnection.HttpResponse(url);
                                parsingData =  JsonParser.UrlJsonForParsing(getContext(),httpResponse);
                                System.out.println(parsingData);
                            }else{
//                            todo we are going to add something
                                System.out.println("There is no internet");
                            }
                        }else{
                            System.out.println("Pleased connect to the internet");
                        }
                        return  parsingData;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
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
}