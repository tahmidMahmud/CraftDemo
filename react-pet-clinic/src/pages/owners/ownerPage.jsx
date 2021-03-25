import React, { Component } from "react";
import OwnerForm from "./ownerForm";
import OwnerTable from "./ownerTable";

async function deleteOwners(ids) {
  const requestOptions = {
    method: "DELETE",
  };
  ids.forEach((element) => {
    fetch(
      "http://localhost:8080/owners/" + element,
      requestOptions
    ).then((response) => response.json());
  });
}

class OwnerPage extends Component {
  state = {
    isLoaded: false,
    ownersForm: false,
    owners: [],
  };

  componentDidMount() {
    this.resetState();
  }

  resetState() {
    fetch("http://localhost:8080/owners")
      .then((res) => res.json())
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            owners: result,
          });
        },
        (error) => {
          this.setState({
            isLoaded: true,
            error,
          });
        }
      )
      .then(this.handleLinks);
  }

  handleOwnersForm = (flag) => {
    this.setState({ ownersForm: flag });
    if (!flag) {
      setTimeout(() => {
        this.setState({ updateValues: null });
      }, 1000);
    }
  };

  handleAddOwner = (newOwner) => {
    console.log(newOwner);
    const requestOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newOwner),
    };
    fetch("http://localhost:8080/owners", requestOptions)
      .then((response) => response.json())
      .then((data) => this.resetState());
  };

  handleUpdateOwner = (newOwner) => {
    console.log(newOwner);
    const requestOptions = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newOwner),
    };
    fetch("http://localhost:8080/owners/" + newOwner.id, requestOptions)
      .then((response) => response.json())
      .then((data) => this.resetState());
  };

  handleUpdateForm = (id) => {
    this.handleOwnersForm(true);
    const updateValues = this.state.owners.filter(
      (owner) => owner.id === id
    )[0];
    this.setState({ updateValues: updateValues });
  };
  handleDeleteOwner = (ids) => {
    deleteOwners(ids).then(() => this.resetState());
  };

  handleLinks = () => {
    const owners = this.state.owners.map((owner) => {
      owner.link = "/owners/" + owner.id;
      return owner;
    });
    this.setState({ owners: owners });
  };

  render() {
    const rows = this.state.owners.map((owner) => {
      owner.name = owner.firstName + " " + owner.lastName;
      return owner;
    });

    return (
      <React.Fragment>
        <OwnerTable
          owners={rows}
          setOpen={this.handleOwnersForm}
          onDelete={this.handleDeleteOwner}
          onUpdateForm={this.handleUpdateForm}
        ></OwnerTable>
        <OwnerForm
          onAdd={this.handleAddOwner}
          onUpdate={this.handleUpdateOwner}
          open={this.state.ownersForm}
          setOpen={this.handleOwnersForm}
          updateValues={this.state.updateValues}
        />
      </React.Fragment>
    );
  }
}

export default OwnerPage;
