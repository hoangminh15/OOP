package DataService;

import Entity.Data;
import java.util.List;

public interface DataBluechipFetcher {
    List<Data> layDataBlueChip(String date);
}
