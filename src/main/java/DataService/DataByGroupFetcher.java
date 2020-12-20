package DataService;

import Entity.Data;

import java.util.List;

public interface DataByGroupFetcher {
    List<Data> layDataTheoGroup(String groupName);
}
