package org.campbell.college_mcp_server;

import io.collegeplanner.my.EllucianDataApplication.model.CoursesRequestModel;
import io.collegeplanner.my.EllucianDataApplication.model.SectionsRequestModel;
import io.collegeplanner.my.EllucianDataApplication.model.SubjectsRequestModel;
import io.collegeplanner.my.EllucianDataApplication.model.TermsRequestModel;
import io.collegeplanner.my.EllucianDataApplication.model.entities.Course;
import io.collegeplanner.my.EllucianDataApplication.model.entities.Section;
import io.collegeplanner.my.EllucianDataApplication.model.entities.Subject;
import io.collegeplanner.my.EllucianDataApplication.model.entities.Term;
import io.collegeplanner.my.EllucianDataApplication.service.EllucianDataService;
import io.collegeplanner.my.EllucianDataApplication.util.Constants;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EllucianTools {

    @Tool(description = "Get the list of supported college identifiers")
    public Set<String> getColleges() {
        return Constants.ELLUCIAN_UNIVERSITIES_SS_DATA_PAGES.keySet();
    }

    @Tool(description = "Get the available registration terms for a college")
    public Set<Term> getTerms(
            @ToolParam(description = "The college identifier") String college) {
        TermsRequestModel request = new TermsRequestModel();
        request.setCollege(college);
        return EllucianDataService.getTerms(request);
    }

    @Tool(description = "Get the list of subjects offered for a given college and term")
    public Set<Subject> getSubjects(
            @ToolParam(description = "The college identifier") String college,
            @ToolParam(description = "The term ID") String term) {
        SubjectsRequestModel request = new SubjectsRequestModel();
        request.setCollege(college);
        request.setTerm(term);
        return EllucianDataService.getSubjects(request);
    }

    @Tool(description = "Get the list of courses for a given college, term, and subject")
    public Set<Course> getCourses(
            @ToolParam(description = "The college identifier") String college,
            @ToolParam(description = "The term ID") String term,
            @ToolParam(description = "The subject abbreviation") String subject) {
        CoursesRequestModel request = new CoursesRequestModel();
        request.setCollege(college);
        request.setTerm(term);
        request.setSubject(subject);
        return EllucianDataService.getCourses(request);
    }

    @Tool(description = "Get the sections and meeting times for a given college, term, subject, and course number")
    public Set<Section> getSections(
            @ToolParam(description = "The college identifier") String college,
            @ToolParam(description = "The term ID") String term,
            @ToolParam(description = "The subject abbreviation") String subject,
            @ToolParam(description = "The course number") String number) {
        SectionsRequestModel request = new SectionsRequestModel();
        request.setCollege(college);
        request.setTerm(term);
        request.setSubject(subject);
        request.setNumber(number);
        return EllucianDataService.getSections(request);
    }
}
