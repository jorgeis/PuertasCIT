package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCliente is a Querydsl query type for Cliente
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCliente extends EntityPathBase<Cliente> {

    private static final long serialVersionUID = 2054315822L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCliente cliente = new QCliente("cliente");

    public final StringPath avatarCli = createString("avatarCli");

    public final SetPath<Contratacion, QContratacion> contratacionSet = this.<Contratacion, QContratacion>createSet("contratacionSet", Contratacion.class, QContratacion.class, PathInits.DIRECT2);

    public final QDireccion direccion;

    public final StringPath emailAltCli = createString("emailAltCli");

    public final DateTimePath<java.sql.Timestamp> fhCreaCli = createDateTime("fhCreaCli", java.sql.Timestamp.class);

    public final DatePath<java.sql.Date> fNacCli = createDate("fNacCli", java.sql.Date.class);

    public final NumberPath<Integer> idCli = createNumber("idCli", Integer.class);

    public final SetPath<MembresiaCliente, QMembresiaCliente> membresiaClienteSet = this.<MembresiaCliente, QMembresiaCliente>createSet("membresiaClienteSet", MembresiaCliente.class, QMembresiaCliente.class, PathInits.DIRECT2);

    public final StringPath objetivoCli = createString("objetivoCli");

    public final StringPath ocupacionCli = createString("ocupacionCli");

    public final SetPath<OrganizacionCliente, QOrganizacionCliente> organizacionClienteSet = this.<OrganizacionCliente, QOrganizacionCliente>createSet("organizacionClienteSet", OrganizacionCliente.class, QOrganizacionCliente.class, PathInits.DIRECT2);

    public final StringPath passAreaCli = createString("passAreaCli");

    public final StringPath passCli = createString("passCli");

    public final QPersona persona;

    public final StringPath sexoCli = createString("sexoCli");

    public final StringPath statusCli = createString("statusCli");

    public final StringPath telFijoCli = createString("telFijoCli");

    public final StringPath telMovilCli = createString("telMovilCli");

    public final SetPath<Uso, QUso> usoSet = this.<Uso, QUso>createSet("usoSet", Uso.class, QUso.class, PathInits.DIRECT2);

    public QCliente(String variable) {
        this(Cliente.class, forVariable(variable), INITS);
    }

    public QCliente(Path<? extends Cliente> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCliente(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCliente(PathMetadata<?> metadata, PathInits inits) {
        this(Cliente.class, metadata, inits);
    }

    public QCliente(Class<? extends Cliente> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.direccion = inits.isInitialized("direccion") ? new QDireccion(forProperty("direccion"), inits.get("direccion")) : null;
        this.persona = inits.isInitialized("persona") ? new QPersona(forProperty("persona"), inits.get("persona")) : null;
    }

}

