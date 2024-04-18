/**
 * @(#)MailInfo.java.
 *
 * Version 1.00.
 */
package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class chua thong tin gui mail
 * 
 *
 *
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {
	String from;
	String to;
	String[] cc;
	String[] bcc;
	String subject;
	String body;
	String[] attachment;
	public MailInfo(String to, String subject, String body) {
		this.from = "BookStore FAHASHA <poly@fpt.edu.vn>";
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}
