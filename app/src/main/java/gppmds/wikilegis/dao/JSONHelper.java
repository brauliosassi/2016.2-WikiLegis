package gppmds.wikilegis.dao;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import gppmds.wikilegis.controller.BillController;
import gppmds.wikilegis.controller.SegmentController;
import gppmds.wikilegis.exception.BillException;
import gppmds.wikilegis.exception.SegmentException;
import gppmds.wikilegis.exception.VotesException;
import gppmds.wikilegis.model.Bill;
import gppmds.wikilegis.model.Segment;
import gppmds.wikilegis.model.Votes;

public class JSONHelper {

    public static String getJSONObjectApi(final String URL) {
        String getApi = null;

        GetRequest request = new GetRequest();

        getApi = request.execute(URL).toString();

        try {
            getApi = request.get().toString();
        } catch (ExecutionException e){
            Log.d("ExecutionException", URL);
        } catch (InterruptedException e){
            Log.d("InterruptedException", URL);
        }
        return getApi;
    }

    public static List<Bill> billListFromJSON(final String billList,
                                              final List<Segment> aux) throws JSONException,
                                               BillException, SegmentException {
        int id;

        List<Bill> billListApi = new ArrayList<>();

        JSONObject bills = new JSONObject(billList);
        JSONArray results = bills.getJSONArray("results");

        Integer numberOfProposals, date;



        for (int index = 0; index < results.length(); index++){

            JSONObject jsonObject = results.getJSONObject(index);

            id = jsonObject.getInt("id");

            numberOfProposals = BillController.countedTheNumberOfProposals(aux, id);

            date= SegmentController.getMinDate(id);

            Bill billAux = BillController.getBill(numberOfProposals, date, jsonObject);

            JSONArray segments = jsonObject.getJSONArray("segments");

            for (int j = 0; j < segments.length(); j++) {

                billAux.setSegments(segments.getInt(j));
            }
            billListApi.add(billAux);
        }

        return billListApi;
    }

    public static List<Votes> votesListFromJSON() throws JSONException, VotesException {

        String url = "http://beta.edemocracia.camara.leg.br/wikilegis/api/votes/?page=1";
        List<Votes> votesListApi = new ArrayList<>();

        do {
            String votesList = getJSONObjectApi(url);

            JSONObject votes = new JSONObject(votesList);
            JSONArray results = votes.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject jsonObject = results.getJSONObject(i);

                Votes voteAux = new Votes(jsonObject.getInt("user"),
                        jsonObject.getInt("content_type"),
                        jsonObject.getInt("object_id"),
                        jsonObject.getString("vote").equals("false") ? false : true);

                votesListApi.add(voteAux);
            }
            String nextUrl = votes.getString("next");
            url = updateDomain(nextUrl);

        } while (!url.equals("null"));

        return votesListApi;
    }


    public static List<Segment> segmentListFromJSON() throws JSONException, SegmentException {

        String url = "http://beta.edemocracia.camara.leg.br/wikilegis/api/segments/";

        List<Segment> segmentListApi = new ArrayList<>();

        do {

            String segmentList = getJSONObjectApi(url);

            JSONObject segment = new JSONObject(segmentList);
            JSONArray results = segment.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject jsonObject = results.getJSONObject(i);

                    Segment segmentAux = new Segment(jsonObject.getInt("id"),
                            jsonObject.getInt("order"),
                            jsonObject.getInt("bill"),
                            jsonObject.getBoolean("original"),
                            jsonObject.getString("replaced").equals("null") ? 0 : jsonObject.getInt("replaced"),
                            jsonObject.getInt("id"),
                            jsonObject.getInt("type"),
                            jsonObject.getString("number").equals("null") ? 0 : jsonObject.getInt("number"),
                            jsonObject.getString("content"),
                            jsonObject.getString("created"));

                segmentListApi.add(segmentAux);
            }
            String nextUrl = segment.getString("next");
            url = updateDomain(nextUrl);

        } while (!url.equals("null"));

        return segmentListApi;
    }

    public static String updateDomain(final String nextUrl){


        if (nextUrl.equals("null"))
            return "null";
        String correctAdress = nextUrl.substring(22);
        String correctDomain = "http://beta.edemocracia.camara.leg.br/" + correctAdress;
        Log.d("Aqui", correctDomain);
        return correctDomain;
    };
}