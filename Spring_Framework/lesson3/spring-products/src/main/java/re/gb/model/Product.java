package re.gb.model;

import lombok.Data;

@Data
public class Product {

    private final Long id;
    private final String title;
    private final double cost;
}

