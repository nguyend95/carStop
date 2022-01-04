package eu.epptec.carStop.entity;

import eu.epptec.carStop.converter.StringToLongConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(schema = "app_db", name = "messages")
@Getter @Setter
public class MessagesEntity {
    @Id
    @Convert(converter = StringToLongConverter.class)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_generator")
    @SequenceGenerator(name = "message_generator", sequenceName = "message_sequence", schema = "app_db")
    private String id;

    @Column(name = "content")
    private String content;

    @Column(name = "received")
    private boolean received;

    @Column(name = "c_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private UserEntity sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private UserEntity receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ride_id", referencedColumnName = "id")
    private RideEntity ride;
}
