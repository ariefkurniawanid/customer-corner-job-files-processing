package id.co.chubb.fileprocess.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse<T> {
    private String responseCode;
    private String responseMessage;
    private T data;
}
