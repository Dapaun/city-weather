import axios from "axios";
import { useState } from "react";
import { CiSearch } from "react-icons/ci";
import { ClockLoader } from "react-spinners";
import { BASE_URL } from "../../constants";

const Search = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [loading, setLoading] = useState(false);
  const [searchResult, setsearchResult] = useState<any>();
  const [invalidError, setinvalidError] = useState(false);
  const [noResultsError, setNoResultsError] = useState(false);

  //http://localhost:8080/api/country?name=Germany

  const handleSearch = () => {
    console.log("Searching for:", searchTerm);
    setinvalidError(!searchTerm.length);
    console.log("Invalid Error State:", invalidError);
    if (!searchTerm.length) return;
    setLoading(true);
    // Implement search functionality here
    setsearchResult(null);
    setNoResultsError(false);
    axios.get(`${BASE_URL}/country?name=${searchTerm}`).then((response) => {
      console.log("Response data:", response.data);
      setsearchResult(response.data);
    }).catch((error) => {
      console.error("Error fetching data:", error);
      setNoResultsError(true);
    }).finally(() => {
      setLoading(false);
    });
  }

  return (
    <>
      <div className="flex border border-gray-300 p-1 rounded-2xl">
        <input
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          type="text"
          placeholder="Search for a country"
          className="rounded-md p-2 w-full outline-none"
          disabled={loading}
        />
        {!loading && <button
          type="button"
          className="mt-2 w-auto cursor-pointer flex hover:text-blue-500 active:text-blue-500"
          onClick={handleSearch}
          disabled={loading}
        >
          <CiSearch className="mt-1" /> <span className="ml-1">Search</span>
        </button>}
        {loading && <ClockLoader className="mt-1.5 mr-3" size={30} color="gray" />}
      </div>
      {invalidError && <p className="text-red-400">Please search a valid city.</p>}
      {noResultsError && <p className="text-red-400">No results found.</p>}
      {searchResult && 
        <div>
          <p className="text-blue-400">The capital of {searchResult.name} is {searchResult.city}. 
            It's a part of {searchResult.region}.</p>
        </div>
      }
    </>
  )
};

export default Search;