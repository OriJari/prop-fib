package algorithm.collaborativeFiltering;

import java.util.*;

import algorithm.K_Means.K_Means;
import algorithm.slopeone.SlopeOne;

/** @class collaborativeFiltering
 * @brief Implements recommendation using K-means and Slope One algorithm.
 */
public class collaborativeFiltering {

    private Map<Integer, TreeMap<Integer, Float>> opinions;
    private Vector<Vector<Integer>> clusters;

    /** @brief <em>opinions</em> represents the ratings, float in the nested Map, that users, the first Integer is their ID, have given about items, their ID is the integer in the nested Map.
     *  <em>clusters</em> represents the k clusters of similar users.
     */

    /** @brief Default builder.
     *
     * \pre <em>true</em>
     * \post Creates a <em>collaborativeFiltering</em> object with <em>opinions</em> and <em>clusters</em> empty.
     */
    public collaborativeFiltering(){
        opinions = new TreeMap<>();
        clusters = new Vector<>();
    }
    /** @brief Builder with defined <em>opinions</em> and <em>k</em>.
     *
     * @param opinions Map that represents the ratings that users have given about some items.
     * @param k Desired number of clusters. Integer larger than 0.
     *
     * \post Creates <em>collaborativeFiltering</em> object with <em>opinions</em> set to opinions and computes the k clusters of users.
     */
    public collaborativeFiltering(Map<Integer, TreeMap<Integer, Float>> opinions, Integer k){
        this.opinions = opinions;
        K_Means Kmean = new K_Means(opinions);
        this.clusters = Kmean.k_means(k);
    }

    /** @brief Function that makes a recommendation of items to a user.
     *
     * @param userID ID of the user that receives the recommendation.
     * @return It returns a Map of item ID's together with the expected rating of the user.
     */
    public Map<Integer, Float> recommend(Integer userID){
        boolean cont = true;
        Integer clusterUser = 0;
        for(int i = 0; i < clusters.size() && cont; ++i){
            for(int j = 0; j < clusters.get(i).size() && cont; ++j){
                if(userID == clusters.get(i).get(j)){
                    clusterUser = i;
                    cont = false;
                }
            }
        }

        TreeMap<Integer, TreeMap<Integer, Float>> valCluster = new TreeMap<Integer, TreeMap<Integer, Float>>();
        for(int i = 0; i < clusters.get(clusterUser).size(); ++i){
            valCluster.put(clusters.get(clusterUser).get(i), opinions.get(clusters.get(clusterUser).get(i)));
        }
        SlopeOne Slopeone = new SlopeOne();
        return SlopeOne.SlopeOne(valCluster, opinions.get(userID));
    }
}
