import React, { Component } from "react";
import VisitForm from "./visitForm";
import VisitTable from "./visitTable";

async function addVisit(visit) {
  const requestOptions = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(visit),
  };
  return fetch("http://localhost:8080/visits", requestOptions);
}

async function updateVisit(visit) {
  const requestOptions = {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(visit),
  };
  return fetch("http://localhost:8080/visits/" + visit.id, requestOptions);
}

async function deleteVisit(ids) {
  const requestOptions = {
    method: "DELETE",
  };
  ids.forEach((element) => {
    fetch(
      "http://localhost:8080/visits/" + element,
      requestOptions
    ).then((response) => response.json());
  });
}

function formatDate(date) {
  let ye = new Intl.DateTimeFormat("en", { year: "numeric" }).format(date);
  let mo = new Intl.DateTimeFormat("en", { month: "2-digit" }).format(date);
  let da = new Intl.DateTimeFormat("en", { day: "2-digit" }).format(date);
  return (
    `${ye}-${mo}-${da}T` + date.toLocaleTimeString("en-US", { hour12: false })
  );
}

class VisitPage extends Component {
  state = { isLoaded: false, visitsForm: false, visits: [] };

  componentDidMount() {
    this.resetState();
  }

  resetState = () => {
    const petId = parseInt(
      new URLSearchParams(this.props.location.search).get("petId")
    );
    const vetId = parseInt(
      new URLSearchParams(this.props.location.search).get("vetId")
    );
    fetch("http://localhost:8080/pets")
      .then((res) => res.json())
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            pets: result,
          });
        },
        // Note: it's important to handle errors here
        // instead of a catch() block so that we don't swallow
        // exceptions from actual bugs in components.
        (error) => {
          this.setState({
            isLoaded: true,
            error,
          });
        }
      );

    fetch("http://localhost:8080/vets")
      .then((res) => res.json())
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            vets: result,
          });
        },
        // Note: it's important to handle errors here
        // instead of a catch() block so that we don't swallow
        // exceptions from actual bugs in components.
        (error) => {
          this.setState({
            isLoaded: true,
            error,
          });
        }
      );

    fetch("http://localhost:8080/visits")
      .then((res) => res.json())
      .then(
        (result) => {
          const filteredResult = this.handleFilterQueryParams(
            petId,
            vetId,
            result
          );
          this.setState({
            isLoaded: true,
            visits: filteredResult,
          });
        },
        // Note: it's important to handle errors here
        // instead of a catch() block so that we don't swallow
        // exceptions from actual bugs in components.
        (error) => {
          this.setState({
            isLoaded: true,
            error,
          });
        }
      );
  };

  handleVisitsForm = (flag) => {
    this.setState({ visitsForm: flag });
    if (!flag) {
      setTimeout(() => {
        this.setState({ updateValues: null });
      }, 1000);
    }
  };

  handleAddVisit = (newVisit) => {
    const visit = { pet: {}, vet: {} };
    visit.pet.id = newVisit.pet;
    visit.vet.id = newVisit.vet;
    visit.description = newVisit.description;
    visit.startDate = formatDate(newVisit.startDate);
    visit.endDate = formatDate(newVisit.endDate);
    console.log(visit);

    const response = addVisit(visit);

    this.resetState();
    return response;
  };

  handleUpdateVisit = (newVisit) => {
    const response = updateVisit(newVisit);
    this.resetState();
    return response;
  };

  handleUpdateForm = (id) => {
    this.handleVisitsForm(true);
    const updateValues = this.state.visits.filter(
      (visit) => visit.id === id
    )[0];
    this.setState({ updateValues: updateValues });
  };

  handleDeleteVisit = (ids) => {
    deleteVisit(ids).then(() => this.resetState());
  };

  handleFilterQueryParams = (petId, vetId, visits) => {
    const newVisits = visits.filter(
      (visit) =>
        (visit.pet.id === petId || isNaN(petId)) &&
        (visit.vet.id === vetId || isNaN(vetId))
    );
    return newVisits;
  };

  render() {
    const petId = parseInt(
      new URLSearchParams(this.props.location.search).get("petId")
    );
    const vetId = parseInt(
      new URLSearchParams(this.props.location.search).get("vetId")
    );
    return (
      <React.Fragment>
        <VisitTable
          visits={this.state.visits}
          vets={this.state.vets}
          pets={this.state.pets}
          setOpen={this.handleVisitsForm}
          onDelete={this.handleDeleteVisit}
          onUpdateForm={this.handleUpdateForm}
        />
        <VisitForm
          vets={this.state.vets}
          pets={this.state.pets}
          initialVet={vetId}
          initialPet={petId}
          onAdd={this.handleAddVisit}
          open={this.state.visitsForm}
          setOpen={this.handleVisitsForm}
          onUpdate={this.handleUpdateVisit}
          updateValues={this.state.updateValues}
        />
      </React.Fragment>
    );
  }
}

export default VisitPage;
