import com.google.gson.annotations.SerializedName;

public class FavoriteAddressParam {

  @SerializedName("id") protected final String id;

  public static SuggestedFavorite suggestedFavorite(String id, String placeType) {
    return new FavoriteAddressParam.SuggestedFavorite(id, placeType);
  }

  private FavoriteAddressParam(String id) {
    this.id = id;
  }

  public static class SuggestedFavorite extends FavoriteAddressParam {
    @SerializedName("place_type") private final String placeType;

    private SuggestedFavorite(String id, String placeType) {
      super(id);
      this.placeType = placeType;
    }
  }
}