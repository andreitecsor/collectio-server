package eco.collectio.dto;

import eco.collectio.domain.Join;
import eco.collectio.domain.Reach;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Achievement {
    private Join join = null;
    private Reach reach = null;
}
