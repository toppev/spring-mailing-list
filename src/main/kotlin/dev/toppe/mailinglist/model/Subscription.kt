package dev.toppe.mailinglist.model

import com.opencsv.bean.CsvIgnore
import java.sql.Date
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email


@Entity
data class Subscription(

        @field:Email(message = "Invalid email address")
        @Id
        val email: String,

        @CsvIgnore
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "mailingList")
        val mailingList: MailingList,

        @Basic
        var date: Date = Date(Calendar.getInstance().time.time)

)