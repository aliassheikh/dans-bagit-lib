package gov.loc.repository.bagit.utilities.namevalue;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public interface NameValueReader extends Iterator<NameValueReader.NameValue> {

	public class NameValue implements Map.Entry<String, String> {
		private String name;
		private String value;
		
		public NameValue(String name, String value) {
			assert name != null;
			this.name = name;
			this.value = value;
		}
			
		public NameValue()	{			
		}
		
		public void setName(String name) {
			assert name != null;
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public String setValue(String value) {
			this.value = value;
			return value;
		}
		
		public String getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			return MessageFormat.format("Name is {0}. Value is {1}.", this.name, this.value);
		}

		@Override
		public String getKey() {
			return this.name;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null) return false;
			if (! (obj instanceof NameValue)) return false;
			NameValue that = (NameValue)obj;
			return Objects.equals(this.name, that.getName()) && Objects.equals(this.value, that.getValue()) && 
			    Objects.equals(this.getKey(), that.getKey());			
		}
		
		@Override
		public int hashCode() {
			return 42 + this.name.hashCode() + (this.value != null ? this.value.hashCode() : 0);
		}
	}	
}
