package com.school.people.controller.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListIdJson implements Serializable {

    private static final long serialVersionUID = -2708776023803508339L;

    private List<String> ids;

}
