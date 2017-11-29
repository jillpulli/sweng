package agile.agile.assembly;

import java.util.HashSet;
import java.util.Set;
import agile.feature.*;
import java.util.Set;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


//cast as a program feature

public class FeatureMatrix {

    FeatureAggregator<String> feature;
    ArrayList<Map<String,String>> featInMatrix;
    ArrayList<Map<String,String>> totalMatrix;

    public FeatureMatrix(FeatureAggregator<String> feat)
    {
        feature = feat;
    }

    public ArrayList<Map<String,String>> featPercentInMatrix()
    {
       // ArrayList<String> keys = new ArrayList(); //stores the keys from FeatureAggregator.
        Set<String> set = feature.keySet();
        for (String csl : set)
        {
            Set<Feature> features = feature.get(csl).getFeatures(); //gets FeatureSet,then gets HashSet of Features to iterator over
            for(Feature feature: features)
            {
                ProgramFeature progFeat= (ProgramFeature) feature; //casts Feature as ProgramFeature
                Map<String,String> map = progFeat.toEntry(); //creates HashMap<String, String>
                featInMatrix.add(map);
            }
        }

        return featInMatrix;
    }


    /*
      @param allDept
      @param feature
      @return totalMatrix
      */
    public ArrayList<Map<String, String>> totalMatrix()
    {
        double curSize = 0;
        double overall = 0;


        Set<String> set = feature.keySet();

        for(String csl: set)
        {
            HashMap<String, String> row = new HashMap<>();
            Set<Feature> features = feature.get(csl).getFeatures();
            for(Feature feature: features)
            {
                ProgramFeature progFeat = (ProgramFeature) feature; //cast Feature as ProgramFeature

                Set<String> dept = progFeat.getProjectNames();                         //returns set of Departments

                for(String deptName: dept)
                {
                    FeatureSet featureSet = (FeatureSet)progFeat.getProject(deptName);
                    curSize = featureSet.getCurrentSize();
                    row.put(deptName, Double.toString(curSize));                    //Add total size to each dept name
                    overall += curSize;
                }

                row.put("Overall", Double.toString(overall));
                overall = 0;
                curSize = 0;
            }
            row.put("CSL_Programs", csl);
            totalMatrix.add(row);
        }


    return totalMatrix;
    }




}
