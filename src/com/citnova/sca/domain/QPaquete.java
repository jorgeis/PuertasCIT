package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPaquete is a Querydsl query type for Paquete
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPaquete extends EntityPathBase<Paquete> {

    private static final long serialVersionUID = 399897301L;

    public static final QPaquete paquete = new QPaquete("paquete");

    public final NumberPath<Float> costo1Paq = createNumber("costo1Paq", Float.class);

    public final NumberPath<Float> costo2Paq = createNumber("costo2Paq", Float.class);

    public final NumberPath<Float> costo3Paq = createNumber("costo3Paq", Float.class);

    public final StringPath descripcionPaq = createString("descripcionPaq");

    public final NumberPath<Integer> idPaq = createNumber("idPaq", Integer.class);

    public final StringPath nombrePaq = createString("nombrePaq");

    public final StringPath statusPaq = createString("statusPaq");

    public QPaquete(String variable) {
        super(Paquete.class, forVariable(variable));
    }

    public QPaquete(Path<? extends Paquete> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPaquete(PathMetadata<?> metadata) {
        super(Paquete.class, metadata);
    }

}

